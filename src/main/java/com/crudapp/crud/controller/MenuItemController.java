package com.crudapp.crud.controller;

import com.crudapp.crud.model.Category;
import com.crudapp.crud.model.MenuItem;
import com.crudapp.crud.repository.CategoryRepository;
import com.crudapp.crud.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menuitems")
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MenuItemController(MenuItemRepository menuItemRepository, CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> listMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> viewMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item ID: " + id));
        return ResponseEntity.ok(menuItem);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMenuItem(@RequestBody MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        return ResponseEntity.ok("Menu item created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        MenuItem existingMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item ID: " + id));

        // Update the existing menu item with the data from menuItem
        existingMenuItem.setItem_name(menuItem.getItem_name());
        existingMenuItem.setItem_description(menuItem.getItem_description());
        existingMenuItem.setItem_price(menuItem.getItem_price());
        existingMenuItem.setCategory(menuItem.getCategory());

        menuItemRepository.save(existingMenuItem);

        return ResponseEntity.ok("Menu item updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
        return ResponseEntity.ok("Menu item deleted successfully");
    }

    @PostMapping("/{id}/set-category/{categoryid}")
    public ResponseEntity<String> setCategoryForMenuItem(
            @PathVariable Long id,
            @PathVariable Long categoryid
    ) {
        MenuItem existingMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item ID: " + id));

        Category category = categoryRepository.findById(categoryid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryid));

        existingMenuItem.setCategory(category);
        menuItemRepository.save(existingMenuItem);

        return ResponseEntity.ok("Category set for the menu item successfully");
    }

}
