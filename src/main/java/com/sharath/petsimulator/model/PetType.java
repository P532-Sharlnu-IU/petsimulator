package com.sharath.petsimulator.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pet_type")
public class PetType {
    public PetType() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;

    public PetType(Object o, String cat, String s) {
    }
}