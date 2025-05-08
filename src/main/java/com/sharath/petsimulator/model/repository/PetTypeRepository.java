package com.sharath.petsimulator.model.repository;

import com.sharath.petsimulator.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}

