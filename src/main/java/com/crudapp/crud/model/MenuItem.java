package com.crudapp.crud.model;

import jakarta.persistence.*;



@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    private String item_name;
    private String item_description;
    private double item_price;

    @Lob
    private byte[] item_picture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Add a category field to represent the relationship

    // Constructors, getters, and setters...

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public byte[] getItem_picture() {
        return item_picture;
    }

    public void setItem_picture(byte[] item_picture) {
        this.item_picture = item_picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
