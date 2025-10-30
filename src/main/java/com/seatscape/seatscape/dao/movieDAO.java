package com.seatscape.seatscape.dao;
import com.seatscape.seatscape.model.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieDAO extends JpaRepository<movie, Integer> {
    @Query(value = "SELECT * from movie m where m.duration >= :duration",nativeQuery = true)
    List<movie> findmoviesgreaterthan(Integer duration);
    @Query(value = "SELECT * from movie m where m.duration <= :duration", nativeQuery = true)
    List<movie> getmovieslesserthan(Integer duration);
    @Query(value = "SELECT * FROM movie m WHERE m.lang = :lang", nativeQuery = true)
    List<movie> getmoviesbylanguage(String lang);
    @Query(value = "SELECT * FROM movie m WHERE m.genre = :genre", nativeQuery = true)
    List<movie> getmoviesbygenre(String genre);
    @Query(value = "SELECT * FROM Movie m WHERE m.title LIKE %:keyword%", nativeQuery = true)
    List<movie> findByTitleContaining(String keyword);
}
