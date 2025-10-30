package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface cinemaDAO extends JpaRepository<cinema, Integer> {

    @Query(value = "SELECT * FROM cinema c where c.city = :cityname", nativeQuery = true)
    List<cinema> findbycity(String cityname);
    @Query(value = "SELECT * FROM cinema c where c.state = :statename", nativeQuery = true)
    List<cinema> findbystate(String statename);
    @Query(value = "SELECT * FROM cinema c WHERE c.state = :statename AND c.city = :cityname", nativeQuery = true)
    List<cinema> getbystatecity(String statename, String cityname);
}
