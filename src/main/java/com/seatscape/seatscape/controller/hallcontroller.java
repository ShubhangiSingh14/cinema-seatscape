package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.hall;
import com.seatscape.seatscape.service.hallservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hall")
public class hallcontroller {
    @Autowired
    hallservice hallservice;
    @GetMapping("getall")
    public ResponseEntity<List<hall>> getallHalls(){
        return hallservice.getallHalls();
    }
    @PutMapping("create/{cinemaid}")
    public ResponseEntity<List<hall>> createHalls(@PathVariable("cinemaid") Integer cinemaid){
        return hallservice.createHalls(cinemaid);
    }
    @GetMapping("get/{cinemaid}")
    public ResponseEntity<List<hall>> gethallsbycinemaid(@PathVariable("cinemaid") Integer cinemaid){
        return hallservice.gethallsbycinemaid(cinemaid);
    }
}
