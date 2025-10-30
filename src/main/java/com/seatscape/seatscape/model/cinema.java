package com.seatscape.seatscape.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class cinema {
    @Id
    private Integer cinemaid;
    private String name;
    private Integer totalhalls;
    private String address;
    private String city;
    private String state;
    @Override
    public String toString() {
        return "cinema{" +
                "cinemaid=" + cinemaid +
                ", name='" + name + '\'' +
                ", totalhalls=" + totalhalls +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(Integer cinemaid) {
        this.cinemaid = cinemaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalhalls() {
        return totalhalls;
    }

    public void setTotalhalls(Integer totalhalls) {
        this.totalhalls = totalhalls;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
