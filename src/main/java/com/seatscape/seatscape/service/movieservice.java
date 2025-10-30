package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.movieDAO;
import com.seatscape.seatscape.model.movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class movieservice {
    @Autowired
    movieDAO movieDAO;
    Object o = new Object();
    public ResponseEntity<List<movie>> getAllMovies() {
        try{
            synchronized (o){
                List<movie> movieList = movieDAO.findAll();
                return new ResponseEntity<>(movieList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Optional<movie>> findbyid(Integer id) {
        try{
             synchronized (o){
                 Optional<movie> m = movieDAO.findById(id);
                 return new ResponseEntity<>(m, HttpStatus.OK);
             }
        }catch (Exception e){
            e.printStackTrace();
        }
        Optional<movie> m = Optional.of(new movie());
        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<movie>> getMoviesgreaterthan(Integer duration) {
        try{
           synchronized (o){
               List<movie> movieList = movieDAO.findmoviesgreaterthan(duration);
               return new ResponseEntity<>(movieList, HttpStatus.OK);
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<movie>> getmovieslesserthan(Integer duration) {
        try {
            synchronized (o){
                List<movie> movieList = movieDAO.getmovieslesserthan(duration);
                return new ResponseEntity<>(movieList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<movie>> getmoviesbylanguage(String lang) {
        try{
            synchronized (o){
                List<movie> movieList = movieDAO.getmoviesbylanguage(lang);
                return new ResponseEntity<>(movieList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<movie>> getmoviesbygenre(String genre) {
        try{
            synchronized (o){
                List<movie> movieList = movieDAO.getmoviesbygenre(genre);
                return new ResponseEntity<>(movieList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<movie>> findByTitleContaining(String keyword) {
        try{
            synchronized (o){
                List<movie> movieList = movieDAO.findByTitleContaining(keyword);
                return new ResponseEntity<>(movieList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deletebyid(Integer id) {
        try{
            synchronized (o){
                movieDAO.deleteById(id);
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> putmovie(movie m) {
        try{
            synchronized (o){
                movieDAO.save(m);
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
