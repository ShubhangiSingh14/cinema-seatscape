package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface hallDAO extends JpaRepository<hall, Integer> {
    List<hall> findAllBycinemaid(Integer cinemaid);

}
