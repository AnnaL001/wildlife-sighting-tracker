package com.anna.wildlife_sighting_tracker.models;

import com.anna.wildlife_sighting_tracker.parameter_resolver.SightingParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SightingParameterResolver.class)
class SightingTest {
  @Test
  @DisplayName("Test to check that a Sighting class instance is instantiated correctly")
  public void newSighting_instantiatesCorrectly_true(Sighting sighting) {
    assertNotNull(sighting);
  }

  @Test
  @DisplayName("Test to check that a Sighting class instance instantiates with locationId")
  public void newSighting_instantiatesWithLocationId(Sighting sighting) {
    assertEquals(1, sighting.getLocationId());
  }

  @Test
  @DisplayName("Test to check that a Sighting class instance instantiates with rangerId")
  public void newSighting_instantiatesWithRangerId(Sighting sighting) {
    assertEquals(1, sighting.getRangerId());
  }

  @Test
  @DisplayName("Test to check that a Sighting class instance instantiates with reported time")
  public void newSighting_instantiatesWithReportedAt(Sighting sighting) {
    assertNotNull(sighting.getReportedAt());
  }
}