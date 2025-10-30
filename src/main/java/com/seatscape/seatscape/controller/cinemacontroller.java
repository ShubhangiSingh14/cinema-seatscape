package com.seatscape.seatscape.controller;
import com.seatscape.seatscape.model.cinema;
import com.seatscape.seatscape.service.cinemaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//{
//        "cinemaid":101,
//        "name": "Regal Cinema",
//        "totalhalls": 1,
//        "address":"384, Chinhpokli Gali, Rampur",
//        "city":"Rampur",
//        "state":"Karnataka"
//}
@RestController
@RequestMapping("cinema")
public class cinemacontroller {
    @Autowired
    cinemaservice cinemaservice;
    @GetMapping("all")
    public ResponseEntity<List<cinema>> getAll(){
        return cinemaservice.getAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<cinema>> getcinemabyid(@PathVariable("id") Integer id){
        return cinemaservice.getbyid(id);
    }
    @GetMapping("city/{cityname}")
    public ResponseEntity<List<cinema>> getbycity(@PathVariable("cityname") String cityname){
        return cinemaservice.getcinemasbycity(cityname);
    }
    @GetMapping("state/{statename}")
    public ResponseEntity<List<cinema>> getbystate(@PathVariable("statename") String statename){
        return cinemaservice.getcinemasbystate(statename);
    }
    @GetMapping("statecity/{statename}/{cityname}")
    public ResponseEntity<List<cinema>> getbystatecity(@PathVariable("statename") String statename, @PathVariable("cityname") String cityname){
        return cinemaservice.getbystatecity(statename,cityname);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletecinemabyid(@PathVariable("id") Integer id){
        return cinemaservice.deletecinemabyid(id);
    }
    @PostMapping("put")
    public ResponseEntity<String> putcinema(@RequestBody cinema c){
        return cinemaservice.putcinema(c);
    }
}
