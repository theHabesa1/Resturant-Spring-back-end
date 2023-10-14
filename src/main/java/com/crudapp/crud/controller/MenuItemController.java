package com.crudapp.crud.controller;

import com.crudapp.crud.model.Category;
import com.crudapp.crud.model.MenuItem;
import com.crudapp.crud.repository.CategoryRepository;
import com.crudapp.crud.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String listMenuItems(Model model) {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        model.addAttribute("menuItems", menuItems);
        return "menuitem/list";
    }

    @GetMapping("/{id}")
    public String viewMenuItem(@PathVariable Long id, Model model) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item ID: " + id));
        model.addAttribute("menuItem", menuItem);
        return "menuitem/view";
    }

    @GetMapping("/create")
    public String createMenuItemForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories); // Pass a list of categories to the form
        return "menuitem/create";
    }

    @PostMapping("/create")
    public String createMenuItem(@ModelAttribute MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        return "redirect:/menuitems";
    }

    @GetMapping("/{id}/edit")
    public String editMenuItemForm(@PathVariable Long id, Model model) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item ID: " + id));
        model.addAttribute("menuItem", menuItem);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories); // Pass a list of categories to the form
        return "menuitem/edit";
    }

    @PostMapping("/{id}/edit")
    public String editMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem) {
        menuItem.setItem_id(id);
        menuItemRepository.save(menuItem);
        return "redirect:/menuitems";
    }

    @GetMapping("/{id}/delete")
    public String deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
        return "redirect:/menuitems";
    }
}
