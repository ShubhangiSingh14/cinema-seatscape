package com.seatscape.seatscape.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@Table(name = "hall")
public class hall {
    @Id
    @Column(name = "hallid")
    private Integer hallid;
    private Integer cinemaid;
    private Integer totalseats;
    public hall(){}

    public hall(Integer hallid, Integer cinemaid, Integer totalseats) {
        this.hallid = hallid;
        this.cinemaid = cinemaid;
        this.totalseats = totalseats;
    }

    @Override
    public String toString() {
        return "hall{" +
                "hallid=" + hallid +
                ", cinemaid=" + cinemaid +
                ", totalseats=" + totalseats +
                '}';
    }

    public Integer getHallid() {
        return hallid;
    }

    public void setHallid(Integer hallid) {
        this.hallid = hallid;
    }

    public Integer getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(Integer cinemaid) {
        this.cinemaid = cinemaid;
    }

    public Integer getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(Integer totalseats) {
        this.totalseats = totalseats;
    }
}
