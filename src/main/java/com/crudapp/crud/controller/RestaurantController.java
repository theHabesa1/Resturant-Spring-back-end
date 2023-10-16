package com.crudapp.crud.controller;

import com.crudapp.crud.model.Category;
import com.crudapp.crud.model.Restaurant;
import com.crudapp.crud.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> listRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }



    @GetMapping("/create")
    public String createRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "restaurant/create";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRestaurant(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok("Restaurant created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID: " + id));
        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID: " + id));



        restaurantRepository.save(existingRestaurant);

        return ResponseEntity.ok(existingRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{restaurantId}/set-category/{categoryId}")
    @Transactional
    public ResponseEntity<String> setRestaurantCategory(
            @PathVariable Long restaurantId,
            @PathVariable Long categoryId
    ) {
        Restaurant restaurant = entityManager.find(Restaurant.class, restaurantId);
        Category category = entityManager.find(Category.class, categoryId);

        if (restaurant == null) {
            return ResponseEntity.badRequest().body("Invalid restaurant ID: " + restaurantId);
        }

        if (category == null) {
            return ResponseEntity.badRequest().body("Invalid category ID: " + categoryId);
        }

        restaurant.setCategory(category);
        entityManager.persist(restaurant);

        return ResponseEntity.ok("Category set successfully for the restaurant.");
    }
}
