package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.Species;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oSpeciesDaoTest {
  private static Sql2oSpeciesDao speciesDao;
  private static Connection connection;

  @BeforeAll
  static void beforeAll() {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    speciesDao = new Sql2oSpeciesDao(sql2o);
    connection = sql2o.open();
  }

  @Test
  @DisplayName("Test that a list of species can be returned")
  public void getAll_returnsListOfSpecies_true() {
    assertEquals(12, speciesDao.getAll().size());
  }

  @Test
  @DisplayName("Test that a species can be returned based on its id")
  public void get_returnsASpecies_true() {
    Species species = speciesDao.get(1);
    assertEquals(1, species.getId());
  }

  @AfterAll
  static void afterAll() {
    connection.close();
  }
}