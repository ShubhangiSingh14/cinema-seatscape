package com.seatscape.seatscape.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private Integer showId;
    private Integer numberOfSeats;
    private String bookedBy;

    @Column(name = "bookedseats")
    private int[] bookedSeats;

    public Ticket(Integer showId, Integer numberOfSeats, String bookedBy, int[] bookedSeats) {
        this.showId = showId;
        this.numberOfSeats = numberOfSeats;
        this.bookedBy = bookedBy;
        this.bookedSeats = bookedSeats;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", showId=" + showId +
                ", numberOfSeats=" + numberOfSeats +
                ", bookedBy='" + bookedBy + '\'' +
                ", bookedSeats=" + Arrays.toString(bookedSeats) +
                '}';
    }

    // Getters & Setters
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public int[] getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int[] bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
