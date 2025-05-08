package com.sharath.petsimulator.inventory;

import java.util.*;

public class Category implements InventoryComponent {
    private final String name;
    private final List<InventoryComponent> children = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
    public void add(InventoryComponent c) {
        children.add(c);
    }

    @Override public String getName() { return name; }
    @Override public int getQuantity() {
        return children.stream().mapToInt(InventoryComponent::getQuantity).sum();
    }
    @Override public Iterator<InventoryComponent> iterator() {
        return children.iterator();
    }

    // for JSON serialization
    public List<InventoryComponent> getChildren() {
        return children;
    }
}
