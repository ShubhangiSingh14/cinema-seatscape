package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TicketDAO extends JpaRepository<Ticket, Integer> {

    // Get all tickets by show ID
    @Query(value = "SELECT * FROM tickets t WHERE t.showid = :showId", nativeQuery = true)
    List<Ticket> getAllByShowId(@Param("showId") Integer showId);

    // Get a specific ticket by ticket ID
    @Query(value = "SELECT * FROM tickets t WHERE t.ticketid = :ticketId", nativeQuery = true)
    Ticket getTicketByTicketId(@Param("ticketId") Integer ticketId);

    // Get all tickets booked by a specific username
    @Query(value = "SELECT * FROM tickets t WHERE t.bookedby = :username", nativeQuery = true)
    List<Ticket> getTicketsByUsername(@Param("username") String username);

    // Update booked seats — ⚠️ better to handle as string or JSON in DB
    @Modifying 
    @Query(value = "UPDATE tickets SET bookedseats = :bookedSeats WHERE ticketid = :ticketId", nativeQuery = true)
    void updateBookedSeats(@Param("bookedSeats") String bookedSeats, @Param("ticketId") Integer ticketId);

    // Update remaining seat count
    @Modifying
    @Query(value = "UPDATE tickets t SET t.numberofseats = :remainingSeats WHERE t.ticketid = :ticketId", nativeQuery = true)
    void setTicketCount(@Param("remainingSeats") Integer remainingSeats, @Param("ticketId") Integer ticketId);
}
