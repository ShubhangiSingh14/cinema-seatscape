package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.FoodItemDAO;
import com.seatscape.seatscape.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemDAO foodItemDAO;

    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        try {
            return new ResponseEntity<>(foodItemDAO.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<FoodItem>> getFoodItemById(Integer id) {
        try {
            Optional<FoodItem> item = foodItemDAO.findById(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<FoodItem>> getFoodItemsByType(String itemType) {
        try {
            return new ResponseEntity<>(foodItemDAO.findByItemType(itemType), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<FoodItem> addFoodItem(FoodItem foodItem) {
        try {
            foodItemDAO.save(foodItem);
            return new ResponseEntity<>(foodItem, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public int getPriceForItem(int id) {
        return foodItemDAO.getPriceByItemId(id);
    }
}
