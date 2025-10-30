package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemDAO extends JpaRepository<FoodItem, Integer> {

    @Query(value = "SELECT * FROM fooditems f WHERE f.item_type = :itemType", nativeQuery = true)
    List<FoodItem> findByItemType(String itemType);

    @Query(value = "SELECT price FROM fooditems f WHERE f.id = :id", nativeQuery = true)
    int getPriceByItemId(int id);
}
