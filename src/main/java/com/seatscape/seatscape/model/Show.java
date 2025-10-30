package com.seatscape.seatscape.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showid")
    private Integer showId;

    @Column(name = "cinemaid")
    private Integer cinemaId;

    @Column(name = "hallid")
    private Integer hallId;

    @Column(name = "movieid")
    private Integer movieId;

    @Column(name = "availableseats")
    private Integer availableSeats;

    @Column(name = "starttime")
    private Timestamp startTime;

    @Column(name = "bookedtickets")
    private String bookedTickets;

    public Show() {}

    public Show(Integer cinemaId, Integer hallId, Integer movieId, Integer availableSeats, Timestamp startTime) {
        this.cinemaId = cinemaId;
        this.hallId = hallId;
        this.movieId = movieId;
        this.availableSeats = availableSeats;
        this.startTime = startTime;
    }
}
