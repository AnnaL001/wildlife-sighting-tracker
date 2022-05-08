package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.Location;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oLocationDaoTest {

  private static Sql2oLocationDao locationDao;
  private static Connection connection;

  @BeforeAll
  static void beforeAll() {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    locationDao = new Sql2oLocationDao(sql2o);
    connection = sql2o.open();
  }

  @Test
  @DisplayName("Test that a list of locations can be returned")
  public void getAll_returnsListOfSpecies_true() {
    assertEquals(6, locationDao.getAll().size());
  }

  @Test
  @DisplayName("Test that a location's data can be returned based on its id")
  public void get_returnsASpecies_true() {
    Location location = locationDao.get(1);
    assertEquals(1, location.getId());
  }

  @AfterAll
  static void afterAll() {
    connection.close();
  }
}