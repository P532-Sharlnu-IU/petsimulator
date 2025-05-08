package com.sharath.petsimulator.inventory;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Item implements InventoryComponent {
    private final String name;
    private int quantity;

    public Item(String name, int qty) {
        this.name = name;
        this.quantity = qty;
    }

    @Override public String getName() { return name; }
    @Override public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }

    @Override
    public Iterator<InventoryComponent> iterator() {
        List<InventoryComponent> list = List.of(this);
        return list.iterator();
    }
}
