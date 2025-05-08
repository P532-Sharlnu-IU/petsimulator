package com.sharath.petsimulator.inventory;

import com.fasterxml.jackson.annotation.*;

import java.util.Iterator;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Category.class, name = "category"),
        @JsonSubTypes.Type(value = Item.class,     name = "item")
})
public interface InventoryComponent {
    String getName();
    int getQuantity();
    Iterator<InventoryComponent> iterator();
}
