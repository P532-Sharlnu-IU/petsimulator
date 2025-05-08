package com.sharath.petsimulator;

import com.sharath.petsimulator.model.Pet;
import com.sharath.petsimulator.model.PetType;
import com.sharath.petsimulator.model.repository.ActivePetSession;
import com.sharath.petsimulator.model.repository.PetRepository;
import com.sharath.petsimulator.model.repository.PetTypeRepository;
import com.sharath.petsimulator.service.InventoryService;
import com.sharath.petsimulator.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {

    @Mock private PetRepository petRepo;
    @Mock private PetTypeRepository typeRepo;
    @Mock private InventoryService inventoryService;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ActivePetSession.getInstance().clear();
    }

    @Test
    void adoptPet_shouldSaveAndReturnNewPet() {
        // build a PetType via no-args + setters
        PetType type = new PetType();
        type.setId(10L);
        type.setName("Dog");
        type.setImageUrl("dog.png");
        when(typeRepo.findById(10L)).thenReturn(Optional.of(type));

        // when saving, assign an ID back
        when(petRepo.save(any(Pet.class))).thenAnswer(inv -> {
            Pet p = inv.getArgument(0);
            p.setId(5L);
            return p;
        });

        // act
        Pet p = petService.adoptPet("Buddy", 10L);

        // assert
        assertThat(p.getId()).isEqualTo(5L);
        assertThat(p.getName()).isEqualTo("Buddy");
        assertThat(p.getImageUrl()).isEqualTo("dog.png");
        assertThat(ActivePetSession.getInstance().getCurrentPet()).isSameAs(p);
        verify(petRepo).save(any(Pet.class));
    }

    @Test
    void getPet_returnsCurrentSessionPet() {
        // arrange
        Pet sessionPet = new Pet("Fluffy");
        sessionPet.setId(1L);
        ActivePetSession.getInstance().setCurrentPet(sessionPet);

        // act
        Pet result = petService.getPet();

        // assert
        assertThat(result).isSameAs(sessionPet);
    }

    @Test
    void getAllPets_callsRepository() {
        // arrange
        Pet a = new Pet("A"), b = new Pet("B");
        a.setId(1L); b.setId(2L);
        when(petRepo.findAll()).thenReturn(List.of(a, b));

        // act
        List<Pet> all = petService.getAllPets();

        // assert
        assertThat(all).containsExactly(a, b);
        verify(petRepo).findAll();
    }

    @Test
    void selectPet_validId_setsSession() {
        // arrange
        Pet p = new Pet("Spike");
        p.setId(2L);
        when(petRepo.findById(2L)).thenReturn(Optional.of(p));

        // act
        Pet result = petService.selectPet(2L);

        // assert
        assertThat(result).isSameAs(p);
        assertThat(ActivePetSession.getInstance().getCurrentPet()).isSameAs(p);
    }

    @Test
    void selectPet_invalidId_throws() {
        when(petRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> petService.selectPet(99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown pet id");
    }

    @Test
    void performAction_unknownAction_throws() {
        // put a pet in session
        ActivePetSession.getInstance().setCurrentPet(new Pet("Ghost"));

        assertThatThrownBy(() -> petService.performAction("fly"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown action");
    }

    @Test
    void undoAction_savesAndReturnsPet() {
        // arrange
        Pet p = new Pet("Echo");
        p.setId(3L);
        ActivePetSession.getInstance().setCurrentPet(p);
        when(petRepo.save(any(Pet.class))).thenReturn(p);

        // act
        Pet result = petService.undoAction();

        // assert
        assertThat(result).isSameAs(p);
        verify(petRepo).save(p);
    }
}