package com.seatscape.seatscape.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class foodorderwrapper {
    private Integer ticketid;
    private String name;
    private Integer showid;
    private int[] items;

    public foodorderwrapper(Integer ticketid, String name, Integer showid, int[] items) {
        this.ticketid = ticketid;
        this.name = name;
        this.showid = showid;
        this.items = items;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShowid() {
        return showid;
    }

    public void setShowid(Integer showid) {
        this.showid = showid;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "foodorderwrapper{" +
                "ticketid=" + ticketid +
                ", name='" + name + '\'' +
                ", showid=" + showid +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
