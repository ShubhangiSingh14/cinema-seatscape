package com.seatscape.seatscape.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seatscape.seatscape.model.FoodOrder;

import java.util.List;

@Repository
public interface FoodOrderDAO extends JpaRepository<FoodOrder, Integer> {
    @Query(value = "SELECT * from foodorders f WHERE f.ticketid = :ticketId LIMIT 1", nativeQuery = true)
    FoodOrder findByTicketId(Integer ticketId);
    @Query(value = "SELECT * from foodorders f WHERE f.showid = :showId", nativeQuery = true)
    List<FoodOrder> findByShowId(Integer showId);
}
