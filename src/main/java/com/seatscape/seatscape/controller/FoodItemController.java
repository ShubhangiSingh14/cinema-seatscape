package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.FoodItem;
import com.seatscape.seatscape.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("/all")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<FoodItem>> getFoodItemById(@PathVariable Integer id) {
        return foodItemService.getFoodItemById(id);
    }

    @GetMapping("/itemtype/{itemType}")
    public ResponseEntity<List<FoodItem>> getFoodItemsByType(@PathVariable("itemType") String itemType) {
        return foodItemService.getFoodItemsByType(itemType);
    }

    @PutMapping("/add")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.addFoodItem(foodItem);
    }
}
