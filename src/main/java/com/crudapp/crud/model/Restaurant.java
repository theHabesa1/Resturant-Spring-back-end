package com.crudapp.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import com.crudapp.crud.model.Category;


import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurant_id;

    private String restaurant_name;
    private String restaurant_description;
    private String restaurant_location;

    @Lob
    private byte[] restaurant_picture;




    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getRestaurantId() {
        return restaurant_id;
    }

    public void setRestaurantId(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurantName() {
        return restaurant_name;
    }

    public void setRestaurantName(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurantDescription() {
        return restaurant_description;
    }

    public void setRestaurantDescription(String restaurant_description) {
        this.restaurant_description = restaurant_description;
    }

    public String getRestaurantLocation() {
        return restaurant_location;
    }

    public void setRestaurantLocation(String restaurant_location) {
        this.restaurant_location = restaurant_location;
    }

    public byte[] getRestaurantPicture() {
        return restaurant_picture;
    }

    public void setRestaurantPicture(byte[] restaurant_picture) {
        this.restaurant_picture = restaurant_picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
