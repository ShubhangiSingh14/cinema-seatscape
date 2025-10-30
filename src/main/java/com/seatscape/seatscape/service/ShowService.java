package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.ShowDAO;
import com.seatscape.seatscape.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowDAO showDAO;

    private final Object lock = new Object();

    public ResponseEntity<List<Show>> getAllShows() {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.findAll();
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Show> getShowById(Integer id) {
    	try {
            Optional<Show> optionalShow = showDAO.findById(id);
            return optionalShow
                    .map(show -> new ResponseEntity<>(show, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getByCinemaId(Integer cinemaId) {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.findByCinemaId(cinemaId);
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getByMovieId(Integer movieId) {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.getByMovieId(movieId);
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getFutureShows() {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.getFutureShows(Timestamp.valueOf(LocalDateTime.now()));
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getFutureShowsByCinemaId(Integer cinemaId) {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.getFutureShowsByCinemaId(Timestamp.valueOf(LocalDateTime.now()), cinemaId);
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getFutureShowsByMovieId(Integer movieId) {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.getFutureShowsByMovieId(Timestamp.valueOf(LocalDateTime.now()), movieId);
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getShowsByCityName(String cityName) {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.getShowsByCityName(cityName);
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Show>> getShowsByCityAndMovieName(String cityName, String movieName) {
        try {
            synchronized (lock) {
                List<Show> shows = showDAO.getShowsByCityAndMovieName(cityName, movieName);
                return new ResponseEntity<>(shows, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
