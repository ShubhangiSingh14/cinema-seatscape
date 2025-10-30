package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.TicketDAO;
import com.seatscape.seatscape.dao.ShowDAO;
import com.seatscape.seatscape.exceptions.*;
import com.seatscape.seatscape.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ShowDAO showDAO;

    private final Object lock = new Object();

    // ----------------- Create Ticket -----------------
    public ResponseEntity<Optional<Ticket>> createTicket(Ticket ticket)
            throws HouseFullException, InsufficientTicketsException, SeatAlreadyBookedException,
            CountMismatchException, CountOfSeatsZero, TooManySeatsException {

        int numSeats = ticket.getNumberOfSeats();

        if (numSeats == 0)
            throw new CountOfSeatsZero("The count of tickets is Zero. Please add some seats and try again.");

        if (numSeats > 9)
            throw new TooManySeatsException("The number of seats is too high. Please select fewer seats.");

        int availableSeats = showDAO.getAvailableSeatsFromShowId(ticket.getShowId());
        if (availableSeats == 0)
            throw new HouseFullException("The show is housefull. Try another show.");

        if (availableSeats < numSeats)
            throw new InsufficientTicketsException("Only " + availableSeats + " tickets are available.");

        if (numSeats != ticket.getBookedSeats().length)
            throw new CountMismatchException("Seat count and selected seat numbers do not match.");

        String bookedSeats = showDAO.getBookedSeatsByShowId(ticket.getShowId());
        StringBuilder sb = new StringBuilder(bookedSeats);

        for (int seatId : ticket.getBookedSeats()) {
            if (seatId < 0 || seatId >= bookedSeats.length() / 2)
                throw new SeatAlreadyBookedException("Seat " + seatId + " is invalid.");

            if (sb.charAt(2 * seatId) != '0')
                throw new SeatAlreadyBookedException("Seat " + seatId + " is already booked.");
            sb.setCharAt(2 * seatId, '1');
        }

        try {
            synchronized (lock) {
                showDAO.updateSeats(ticket.getShowId(), availableSeats - numSeats);
                showDAO.setBookedSeats(sb.toString(), ticket.getShowId());
                ticketDAO.save(ticket);
                return new ResponseEntity<>(Optional.of(ticket), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ----------------- Get Tickets -----------------
    public ResponseEntity<List<Ticket>> getAllByShowId(Integer showId) {
        try {
            synchronized (lock) {
                return new ResponseEntity<>(ticketDAO.getAllByShowId(showId), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<Ticket>> getTicketByTicketId(Integer ticketId) {
        try {
            synchronized (lock) {
                return new ResponseEntity<>(Optional.ofNullable(ticketDAO.getTicketByTicketId(ticketId)), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Ticket>> getTicketsByUsername(String username) {
        try {
            synchronized (lock) {
                return new ResponseEntity<>(ticketDAO.getTicketsByUsername(username), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Ticket>> getAllTickets() {
        try {
            synchronized (lock) {
                return new ResponseEntity<>(ticketDAO.findAll(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ----------------- Cancel Ticket -----------------
    public ResponseEntity<String> cancelTicket(Integer id)
            throws TicketDoesNotExistException, SeatsAreInconsistentStateException {

        Optional<Ticket> optionalTicket = ticketDAO.findById(id);
        if (optionalTicket.isEmpty())
            throw new TicketDoesNotExistException("The ticket you're trying to cancel does not exist.");

        Ticket ticket = optionalTicket.get();
        int showId = ticket.getShowId();
        int numSeats = ticket.getNumberOfSeats();
        int[] seatsToFree = ticket.getBookedSeats();

        String seatsBooked = showDAO.getBookedSeatsByShowId(showId);
        StringBuilder sb = new StringBuilder(seatsBooked);

        for (int seat : seatsToFree) {
            if (seat < 0 || seat >= sb.length() / 2 || sb.charAt(2 * seat) != '1')
                throw new SeatsAreInconsistentStateException("Seat state inconsistent.");
            sb.setCharAt(2 * seat, '0');
        }

        try {
            synchronized (lock) {
                ticketDAO.deleteById(id);
                showDAO.updateSeats(showId, showDAO.getAvailableSeatsFromShowId(showId) + numSeats);
                showDAO.setBookedSeats(sb.toString(), showId);
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
    }

    // ----------------- Partial Cancellation -----------------
    public ResponseEntity<String> partialCancellation(Ticket ticket) throws InvalidTicketException {
        Optional<Ticket> optionalTicket = ticketDAO.findById(ticket.getTicketId());
        if (optionalTicket.isEmpty())
            throw new InvalidTicketException("The ticket you're trying to cancel is invalid.");

        Ticket dbTicket = optionalTicket.get();
        int requestedSeats = ticket.getNumberOfSeats();

        if (requestedSeats > dbTicket.getNumberOfSeats())
            return new ResponseEntity<>("Cancellation seats exceed booked seats.", HttpStatus.BAD_REQUEST);

        if (requestedSeats == dbTicket.getNumberOfSeats())
            return new ResponseEntity<>("Same seat count. Please use complete cancellation.", HttpStatus.BAD_REQUEST);

        int[] seatsToCancel = ticket.getBookedSeats();
        int[] bookedSeats = dbTicket.getBookedSeats();
        Arrays.sort(bookedSeats);

        String bookedSeatsString = showDAO.getBookedSeatsByShowId(ticket.getShowId());
        StringBuilder sb = new StringBuilder(bookedSeatsString);
        List<Integer> invalidSeats = new ArrayList<>();

        for (int seat : seatsToCancel) {
            if (Arrays.binarySearch(bookedSeats, seat) < 0) {
                invalidSeats.add(seat);
            } else {
                sb.setCharAt(2 * seat, '0');
            }
        }

        if (!invalidSeats.isEmpty())
            return new ResponseEntity<>("Seats not booked by you: " + invalidSeats, HttpStatus.BAD_REQUEST);

        List<Integer> remainingSeats = new ArrayList<>();
        for (int seat : bookedSeats) {
            if (Arrays.binarySearch(seatsToCancel, seat) < 0)
                remainingSeats.add(seat);
        }

        int[] resultSeats = remainingSeats.stream().mapToInt(Integer::intValue).toArray();
        int remainingSeatCount = dbTicket.getNumberOfSeats() - requestedSeats;

        try {
            synchronized (lock) {
                showDAO.updateSeats(ticket.getShowId(),
                        showDAO.getAvailableSeatsFromShowId(ticket.getShowId()) + requestedSeats);
                showDAO.setBookedSeats(sb.toString(), ticket.getShowId());
             // Convert int[] resultSeats to String format for DB
                StringBuilder sbResultSeats = new StringBuilder();
                for (int seat : resultSeats) {
                    sbResultSeats.append('1').append(' '); // adjust separator if needed
                }
                String resultSeatsString = sbResultSeats.toString().trim();

                // Update booked seats and remaining seat count in ticket
                ticketDAO.updateBookedSeats(resultSeatsString, dbTicket.getTicketId());
                ticketDAO.setTicketCount(remainingSeatCount, dbTicket.getTicketId());
            }
            return new ResponseEntity<>("SUCCESSFULLY CANCELLED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ----------------- Available Seats -----------------
    public ResponseEntity<List<Integer>> getAvailableSeatsForShowId(Integer showId) {
        String bookedSeats = showDAO.getBookedSeatsByShowId(showId);
        List<Integer> availableSeats = new ArrayList<>();

        for (int i = 0; i < bookedSeats.length() / 2; i++) {
            if (bookedSeats.charAt(i * 2) == '0') {
                availableSeats.add(i);
            }
        }

        try {
            return new ResponseEntity<>(availableSeats, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
