package com.anna.wildlife_sighting_tracker.models;

import com.anna.wildlife_sighting_tracker.models.parameter_resolver.ThrivingAnimalParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ThrivingAnimalParameterResolver.class)
class ThrivingAnimalTest {
  @Test
  @DisplayName("Test to check that a ThrivingAnimal class instance is instantiated correctly")
  public void newThrivingAnimal_instantiatesCorrectly_true(ThrivingAnimal thrivingAnimal) {
    assertNotNull(thrivingAnimal);
  }

  @Test
  @DisplayName("Test to check that a ThrivingAnimal class instance instantiates with an image url")
  public void newThrivingAnimal_instantiatesWithAnImageUrl_true(ThrivingAnimal thrivingAnimal) {
    assertEquals("https://upload.wikimedia.org/wikipedia/commons/1/1e/Cecil_the_lion_at_Hwange_National_Park_%284516560206%29.jpg", thrivingAnimal.getImageUrl());
  }

  @Test
  @DisplayName("Test to check that a ThrivingAnimal class instance instantiates with a name")
  public void newThrivingAnimal_instantiatesWithAName_true(ThrivingAnimal thrivingAnimal) {
    assertEquals("Lion King", thrivingAnimal.getName());
  }

  @Test
  @DisplayName("Test to check that a ThrivingAnimal class instance instantiates with a speciesId")
  public void newThrivingAnimal_instantiatesWithSpeciesId_true(ThrivingAnimal thrivingAnimal) {
    assertEquals(1, thrivingAnimal.getSpeciesId());
  }

  @Test
  public void newThrivingAnimal_instantiatesWithCategory_true(ThrivingAnimal thrivingAnimal) {
    assertEquals(ThrivingAnimal.ANIMAL_CATEGORY, thrivingAnimal.getCategory());
  }
}