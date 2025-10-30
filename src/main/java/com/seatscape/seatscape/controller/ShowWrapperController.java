package com.seatscape.seatscape.controller;
import com.seatscape.seatscape.model.ShowWrapper;
import com.seatscape.seatscape.service.ShowWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowWrapperController {

    @Autowired
    private ShowWrapperService showWrapperService;

    @PutMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowWrapper showWrapper) {
        return showWrapperService.addToDB(showWrapper);
    }
}
