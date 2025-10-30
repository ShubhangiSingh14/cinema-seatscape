package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.exceptions.FoodOrderAlreadyExistsException;
import com.seatscape.seatscape.exceptions.FoodOrderEmptyException;
import com.seatscape.seatscape.exceptions.TicketDoesNotExistException;
import com.seatscape.seatscape.model.FoodOrder;
import com.seatscape.seatscape.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodorder")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @GetMapping("/all")
    public ResponseEntity<List<FoodOrder>> getAllFoodOrders() {
        return foodOrderService.getAllOrders();
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Optional<FoodOrder>> getFoodOrderByTicketId(@PathVariable("ticketId") Integer ticketId) {
        return foodOrderService.getFoodOrderByTicketId(ticketId);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<FoodOrder>> getFoodOrderById(@PathVariable("id") Integer id) {
        return foodOrderService.getFoodOrderById(id);
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<FoodOrder>> getFoodOrdersByShowId(@PathVariable("showId") Integer showId) {
        return foodOrderService.getFoodOrdersByShowId(showId);
    }

    @PutMapping("/place")
    public ResponseEntity<FoodOrder> placeFoodOrder(@RequestBody FoodOrder foodOrder)
            throws FoodOrderAlreadyExistsException, FoodOrderEmptyException, TicketDoesNotExistException {
        return foodOrderService.placeFoodOrder(foodOrder);
    }
}
