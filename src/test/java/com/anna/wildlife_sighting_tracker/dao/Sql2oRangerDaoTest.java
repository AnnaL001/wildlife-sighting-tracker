package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.Ranger;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oRangerDaoTest {
  private static Sql2oRangerDao rangerDao;
  private static Connection connection;

  @BeforeAll
  static void beforeAll() {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    rangerDao = new Sql2oRangerDao(sql2o);
    connection = sql2o.open();
  }

  @Test
  @DisplayName("Test that a list of rangers can be returned")
  public void getAll_returnsListOfSpecies_true() {
    assertEquals(10, rangerDao.getAll().size());
  }

  @Test
  @DisplayName("Test that a ranger's data can be returned based on id specified")
  public void get_returnsASpecies_true() {
    Ranger ranger = rangerDao.get(1);
    assertEquals(1, ranger.getId());
  }

  @AfterAll
  static void afterAll() {
    connection.close();
  }
}