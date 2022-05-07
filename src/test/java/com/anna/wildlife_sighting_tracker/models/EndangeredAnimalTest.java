package com.anna.wildlife_sighting_tracker.models;

import com.anna.wildlife_sighting_tracker.models.parameter_resolver.EndangeredAnimalParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EndangeredAnimalParameterResolver.class)
class EndangeredAnimalTest {
  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance is instantiated correctly")
  public void newEndangeredAnimal_instantiatesCorrectly_true(EndangeredAnimal endangeredAnimal) {
    assertNotNull(endangeredAnimal);
  }

  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance instantiates with an imageUrl")
  public void newEndangeredAnimal_instantiatesWithImageUrl_true(EndangeredAnimal endangeredAnimal) {
    assertEquals("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm8oUUKb6GMiSeAnjvgDYnnFM1OAWPIMzqcm6-tyuEG6MxwGv8NMCdxkC8wLWOjJf7qs0&usqp=CAU", endangeredAnimal.getImageUrl());
  }

  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance instantiates with a name")
  public void newEndangeredAnimal_instantiatesWithName_true(EndangeredAnimal endangeredAnimal) {
    assertEquals("Zippy", endangeredAnimal.getName());
  }

  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance instantiates with a speciesId")
  public void newEndangeredAnimal_instantiatesWithSpeciesId_true(EndangeredAnimal endangeredAnimal) {
    assertEquals(2, endangeredAnimal.getSpeciesId());
  }

  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance instantiates with a healthStatus")
  public void newEndangeredAnimal_instantiatesWithHealthStatus_true(EndangeredAnimal endangeredAnimal) {
    assertEquals(EndangeredAnimal.OKAY, endangeredAnimal.getHealthStatus());
  }

  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance instantiates with an age")
  public void newEndangeredAnimal_instantiatesWithAge_true(EndangeredAnimal endangeredAnimal) {
    assertEquals(EndangeredAnimal.YOUNG, endangeredAnimal.getAge());
  }

  @Test
  @DisplayName("Test to check that an EndangeredAnimal class instance instantiates with an animal category")
  public void newEndangeredAnimal_instantiatesWithCategory_true(EndangeredAnimal endangeredAnimal) {
    assertEquals(EndangeredAnimal.ANIMAL_CATEGORY, endangeredAnimal.getCategory());
  }
}