package com.anna.wildlife_sighting_tracker.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
  private static Location location;

  @BeforeEach
  public void setUp() {
    location = new Location("Zone A", "Marshlands");
  }

  @Test
  @DisplayName("Test that Location instance instantiates correctly")
  public void newLocation_instantiatesCorrectly_true() {
    assertNotNull(location);
  }

  @Test
  @DisplayName("Test that Location instance instantiates with name")
  public void newLocation_instantiatesWithName_true() {
    assertEquals("Zone A", location.getName());
  }

  @Test
  @DisplayName("Test that Location instance instantiates with description")
  public void newLocation_instantiatesWithDescription_true() {
    assertEquals("Marshlands", location.getDescription());
  }
}