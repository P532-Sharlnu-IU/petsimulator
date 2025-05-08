package com.sharath.petsimulator.controller;

import com.sharath.petsimulator.dto.RestockRequest;
import com.sharath.petsimulator.inventory.Category;
import com.sharath.petsimulator.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins="*")
public class InventoryController {
    private final InventoryService svc;
    public InventoryController(InventoryService svc) {
        this.svc = svc;
    }

    @GetMapping
    public Category inventory() {
        return svc.getInventory();
    }

    @PostMapping("/restock")
    public void restock(@RequestBody RestockRequest req) {
        svc.restock(req.getName(), req.getQuantity());
    }
}
