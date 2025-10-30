package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.exceptions.*;
import com.seatscape.seatscape.model.Ticket;
import com.seatscape.seatscape.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PutMapping("/create")
    public ResponseEntity<Optional<Ticket>> createTicket(@RequestBody Ticket ticket)
            throws HouseFullException, InsufficientTicketsException, SeatAlreadyBookedException,
            CountMismatchException, CountOfSeatsZero, TooManySeatsException {
        return ticketService.createTicket(ticket);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<Ticket>> getAllByShowId(@PathVariable("showId") Integer showId) {
        return ticketService.getAllByShowId(showId);
    }

    @GetMapping("/id/{ticketId}")
    public ResponseEntity<Optional<Ticket>> getTicketByTicketId(@PathVariable("ticketId") Integer ticketId) {
        return ticketService.getTicketByTicketId(ticketId);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Ticket>> getTicketsByUsername(@PathVariable("username") String username) {
        return ticketService.getTicketsByUsername(username);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelTicket(@PathVariable("id") Integer id)
            throws TicketDoesNotExistException, SeatsAreInconsistentStateException {
        return ticketService.cancelTicket(id);
    }

    @DeleteMapping("/cancel/partial")
    public ResponseEntity<String> cancelPartial(@RequestBody Ticket ticket) throws InvalidTicketException {
        return ticketService.partialCancellation(ticket);
    }

    @GetMapping("/show/{showId}/availableseats")
    public ResponseEntity<List<Integer>> availableSeatsForShowId(@PathVariable("showId") Integer showId) {
        return ticketService.getAvailableSeatsForShowId(showId);
    }
}
