package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.movie;
import com.seatscape.seatscape.service.movieservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//        {
//          "movieid": 83,
//          "title": "MySQL Internals",
//          "description": "An In-Depth Explanation of famous ORM DB MySQL",
//          "duration": 183,
//          "lang": "English",
//          "genre": "Educational",
//          "releasedate": "1995-05-31T00:00:00"
//        }

@RestController
@RequestMapping("movie")
public class moviecontroller {
    //Movie Service
    @Autowired
    movieservice movieservice;
    //Get Movie Data
    @GetMapping("getAll")
    public ResponseEntity<List<movie>> getAllMovies(){
        return movieservice.getAllMovies();
    }
    @GetMapping("get/{id}")
    public ResponseEntity<Optional<movie>> getmovieById(@PathVariable("id") Integer id){
        return movieservice.findbyid(id);
    }
    @GetMapping("get/duration/greater/{duration}")
    public ResponseEntity<List<movie>> getMoviesgreaterthan(@PathVariable("duration") Integer duration){
        return movieservice.getMoviesgreaterthan(duration);
    }
    @GetMapping("get/duration/lesser/{duration}")
    public ResponseEntity<List<movie>> getmovieslesserthan(@PathVariable("duration") Integer duration){
        return movieservice.getmovieslesserthan(duration);
    }
    @GetMapping("get/language/{lang}")
    public ResponseEntity<List<movie>> getmoviesbylanguage(@PathVariable("lang") String lang){
        return movieservice.getmoviesbylanguage(lang);
    }
    @GetMapping("get/genre/{genre}")
    public ResponseEntity<List<movie>> getmoviesbygenre(@PathVariable("genre") String genre){
        return movieservice.getmoviesbygenre(genre);
    }
    @GetMapping("get/title/{keyword}")
    public ResponseEntity<List<movie>> findByTitleContaining(@PathVariable("keyword") String keyword){
        return movieservice.findByTitleContaining(keyword);
    }
    //Delete Movie
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable("id") Integer id){
        return movieservice.deletebyid(id);
    }
    @PostMapping("put")
    public ResponseEntity<String>putmovie(@RequestBody movie m){
        return movieservice.putmovie(m);
    }


}
