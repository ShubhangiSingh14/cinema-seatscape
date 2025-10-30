package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.cinemaDAO;
import com.seatscape.seatscape.model.cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class cinemaservice {
    @Autowired
    cinemaDAO cinemaDAO;
    Object o = new Object();
    public ResponseEntity<List<cinema>> getAll() {
        try{
            synchronized (o){
                return new ResponseEntity<>(cinemaDAO.findAll(), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<cinema>> getcinemasbycity(String cityname) {
        try{
            synchronized (o){
                return new ResponseEntity<>(cinemaDAO.findbycity(cityname), HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<cinema>> getcinemasbystate(String statename) {
        try{
            synchronized (o){
                return new ResponseEntity<>(cinemaDAO.findbystate(statename), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<cinema>> getbystatecity(String statename, String cityname) {
        try{
            synchronized (o){
                return new ResponseEntity<>(cinemaDAO.getbystatecity(statename, cityname), HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<cinema>> getbyid(Integer id) {
        try {
            synchronized (o){
                Optional<cinema> op = cinemaDAO.findById(id);
                if (op.isPresent()) {
                    return new ResponseEntity<>(op, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(op, HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deletecinemabyid(Integer id) {
        try{
            synchronized (o){
                cinemaDAO.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> putcinema(cinema c) {
        try{
            synchronized (o){
                cinemaDAO.save(c);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
