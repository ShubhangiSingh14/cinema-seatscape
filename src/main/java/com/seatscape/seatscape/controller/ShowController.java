package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.Show;
import com.seatscape.seatscape.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("all")
    public ResponseEntity<List<Show>> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable("id") Integer id) {
        return showService.getShowById(id);
    }

    @GetMapping("cid/{cinemaId}")
    public ResponseEntity<List<Show>> getShowsByCinemaId(@PathVariable("cinemaId") Integer cinemaId) {
        return showService.getByCinemaId(cinemaId);
    }

    @GetMapping("mid/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovieId(@PathVariable("movieId") Integer movieId) {
        return showService.getByMovieId(movieId);
    }

    @GetMapping("future")
    public ResponseEntity<List<Show>> getFutureShows() {
        return showService.getFutureShows();
    }

    @GetMapping("future/{cinemaId}")
    public ResponseEntity<List<Show>> getFutureShowsByCinemaId(@PathVariable("cinemaId") Integer cinemaId) {
        return showService.getFutureShowsByCinemaId(cinemaId);
    }

    @GetMapping("future/mid/{movieId}")
    public ResponseEntity<List<Show>> getFutureShowsByMovieId(@PathVariable("movieId") Integer movieId) {
        return showService.getFutureShowsByMovieId(movieId);
    }

    @GetMapping("city/{cityName}")
    public ResponseEntity<List<Show>> getShowsByCityName(@PathVariable("cityName") String cityName) {
        return showService.getShowsByCityName(cityName);
    }

    @GetMapping("citymovie/{cityName}/{movieName}")
    public ResponseEntity<List<Show>> getShowsByCityAndMovieName(
            @PathVariable("cityName") String cityName,
            @PathVariable("movieName") String movieName) {
        return showService.getShowsByCityAndMovieName(cityName, movieName);
    }
}
