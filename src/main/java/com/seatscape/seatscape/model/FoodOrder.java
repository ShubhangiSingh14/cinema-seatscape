package com.seatscape.seatscape.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "foodorders")
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ticketId;

    @Column(name = "items")
    private int[] items;  // store item IDs, or use @ElementCollection for JPA mapping

    private boolean paid;

    private int totalPrice;

    public FoodOrder() {
    }

    public FoodOrder(Integer ticketId, int[] items, boolean paid, int totalPrice) {
        this.ticketId = ticketId;
        this.items = items;
        this.paid = paid;
        this.totalPrice = totalPrice;
    }

    // Getter and Setter methods
    public Integer getTicketId() { return ticketId; }
    public void setTicketId(Integer ticketId) { this.ticketId = ticketId; }

    public int[] getItems() { return items; }
    public void setItems(int[] items) { this.items = items; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
}
