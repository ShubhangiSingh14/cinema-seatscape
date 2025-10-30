package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.ShowDAO;
import com.seatscape.seatscape.model.Show;
import com.seatscape.seatscape.model.ShowWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ShowWrapperService {

    @Autowired
    private ShowDAO showDAO;

    private final Object lock = new Object();

    public ResponseEntity<String> addToDB(ShowWrapper showWrapper) {
        try {
            // Use getters from ShowWrapper
            Integer hallId = showWrapper.getHallId();
            Integer cinemaId = showWrapper.getCinemaId();
            Integer movieId = showWrapper.getMovieId();
            Integer availableSeats = showWrapper.getAvailableSeats();
            Integer showYear = showWrapper.getStartYear();
            Integer showMonth = showWrapper.getStartMonth();
            Integer showDate = showWrapper.getStartDay();
            Integer startHour = showWrapper.getStartHour();
            Integer startMin = showWrapper.getStartMin();

            // Create timestamp
            Timestamp startTime = Timestamp.valueOf(
                    LocalDateTime.of(showYear, showMonth, showDate, startHour, startMin)
            );

            // Create Show object
            Show show = new Show(cinemaId, hallId, movieId, availableSeats, startTime);

            synchronized (lock) {
                showDAO.save(show);
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cannot save show", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
