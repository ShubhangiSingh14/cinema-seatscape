package com.seatscape.seatscape.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fooditems")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Integer price;

    @Column(name = "itemtype")
    private String itemType;

    // ✅ Add this manual no-argument constructor
    public FoodItem() {
    }

    // ✅ Keep your existing full constructor
    public FoodItem(String name, String description, Integer price, String itemType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.itemType = itemType;
    }

    // ✅ Generate getters and setters manually if Lombok still doesn’t work
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
