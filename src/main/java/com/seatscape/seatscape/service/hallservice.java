package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.hallDAO;
import com.seatscape.seatscape.model.cinema;
import com.seatscape.seatscape.model.hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class hallservice {
    @Autowired
    cinemaservice cinemaservice;
    @Autowired
    hallDAO hallDAO;
    Object o = new Object();
    public ResponseEntity<List<hall>> getallHalls() {
        try{
            synchronized (o){
                return new ResponseEntity<>(hallDAO.findAll(), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<hall>> createHalls(Integer cinemaid) {
        ResponseEntity<Optional<cinema>> c = cinemaservice.getbyid(cinemaid);
        if(c.getBody().isPresent()){
            Integer totalHalls = c.getBody().get().getTotalhalls();
            List<hall> listHalls = new ArrayList<>();
            for(int i = 1;i<=totalHalls;i++){
                listHalls.add(new hall(10 * cinemaid + i , cinemaid, 99));
            }
            try{
                synchronized (o){
                    hallDAO.saveAll(listHalls);
                    return new ResponseEntity<>(listHalls, HttpStatus.CREATED);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>( HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<hall>> gethallsbycinemaid(Integer cinemaid) {
        try{
            synchronized (o){
                return new ResponseEntity<>(hallDAO.findAllBycinemaid(cinemaid), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
