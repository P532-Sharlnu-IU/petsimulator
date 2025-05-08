package com.sharath.petsimulator.service;

import com.sharath.petsimulator.inventory.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    private final InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    /** Bootstrap initial items */
    @PostConstruct
    public void init() {
        if (repo.count() == 0) {
            repo.save(new InventoryItem(null, "Food", "Pet Food", 5));
            repo.save(new InventoryItem(null, "Food", "Treat",    3));
            repo.save(new InventoryItem(null, "Toys", "Ball",      1));
            repo.save(new InventoryItem(null, "Toys", "Chew Toy",  2));
            repo.save(new InventoryItem(null, "Medicine", "Med Pack", 3));
        }
    }

    /** Return a Composite tree of all items */
    public Category getInventory() {
        List<InventoryItem> items = repo.findAll();
        Category root = new Category("Inventory");

        Map<String, Category> cats = new HashMap<>();
        for (InventoryItem it : items) {
            Category cat = cats.computeIfAbsent(
                    it.getCategory(),
                    k -> new Category(k)
            );
            cat.add(new Item(it.getName(), it.getQuantity()));
        }
        cats.values().forEach(root::add);
        return root;
    }

    /** Remove qty of a named item from stock */
    @Transactional
    public void consume(String itemName, int qty) {
        InventoryItem it = repo.findByName(itemName)
                .orElseThrow(() ->
                        new IllegalArgumentException("No such item: " + itemName)
                );
        if (it.getQuantity() < qty) {
            throw new IllegalStateException(itemName + " out of stock");
        }
        it.setQuantity(it.getQuantity() - qty);
        repo.save(it);
    }
}