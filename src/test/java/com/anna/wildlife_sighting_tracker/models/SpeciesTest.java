package com.anna.wildlife_sighting_tracker.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeciesTest {
  private Species species;

  @BeforeEach
  public void setUp() {
    species = new Species("Lion");
  }

  @Test
  @DisplayName("Test that Species class instance instantiates correctly")
  public void newSpecies_instantiatesCorrectly_true() {
    assertTrue(species instanceof Species);
  }

  @Test
  @DisplayName("Test that Species class instance instantiates with name")
  void newSpecies_instantiatesWithName_true() {
    assertEquals("Lion", species.getName());
  }
}