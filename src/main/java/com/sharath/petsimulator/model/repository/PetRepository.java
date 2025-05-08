package com.sharath.petsimulator.model.repository;

import com.sharath.petsimulator.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {}
