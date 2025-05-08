package com.sharath.petsimulator.inventory;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_item")
@Getter @Setter @NoArgsConstructor
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // <— add this by hand:
    public InventoryItem(Long id, String category, String name, int quantity) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
    }

    /** Category grouping (e.g. "Food", "Toys") */
    private String category;

    /** Item name (e.g. "Pet Food", "Ball") */
    private String name;

    /** Current stock */
    private int quantity;
}