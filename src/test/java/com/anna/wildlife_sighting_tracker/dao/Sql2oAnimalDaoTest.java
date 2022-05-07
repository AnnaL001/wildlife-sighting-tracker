package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.base.WildlifeTrackerDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oAnimalDaoTest {
  private static Sql2oAnimalDao animalDao;
  private static Connection connection;
  @BeforeAll
  static void beforeAll() {
    WildlifeTrackerDB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    animalDao = new Sql2oAnimalDao(WildlifeTrackerDB.sql2o);
    connection = WildlifeTrackerDB.sql2o.open();
  }

  @AfterEach
  public void tearDown() {
    animalDao.deleteAll();
  }

  @AfterAll
  static void afterAll() {
    connection.close();
    System.out.println("Connection closed");
  }
}