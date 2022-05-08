package com.anna.wildlife_sighting_tracker.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangerTest {
  private static Ranger ranger;

  @BeforeEach
  public void setUp() {
    ranger = new Ranger("John Doe", "RG_11111", 765770989, "johndoe@forestservice.com");
  }

  @Test
  @DisplayName("Test that Ranger class instance instantiates correctly")
  public void newRanger_instantiatesCorrectly() {
    assertNotNull(ranger);
  }

  @Test
  @DisplayName("Test that Ranger class instance instantiates with name")
  public void newRanger_instantiatesWithName() {
    assertEquals("John Doe", ranger.getName());
  }

  @Test
  @DisplayName("Test that Ranger class instance instantiates with badge no")
  public void newRanger_instantiatesWithBadge() {
    assertEquals("RG_11111", ranger.getBadge());
  }

  @Test
  @DisplayName("Test that Ranger class instance instantiates with phone")
  public void newRanger_instantiatesWithPhone() {
    assertEquals(765770989, ranger.getPhone());
  }
  @Test
  @DisplayName("Test that Ranger class instance instantiates with email")
  public void newRanger_instantiatesWithEmail() {
    assertEquals("johndoe@forestservice.com", ranger.getEmail());
  }
}