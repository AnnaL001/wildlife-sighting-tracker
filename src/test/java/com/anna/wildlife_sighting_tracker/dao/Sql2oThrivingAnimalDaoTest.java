package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.ThrivingAnimal;
import com.anna.wildlife_sighting_tracker.parameter_resolver.ThrivingAnimalParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ThrivingAnimalParameterResolver.class)
class Sql2oThrivingAnimalDaoTest {
  private static Sql2oThrivingAnimalDao animalDao;
  private static Connection connection;

  @BeforeAll
  static void beforeAll() {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    animalDao = new Sql2oThrivingAnimalDao(sql2o);
    connection = sql2o.open();
  }

  @Test
  @DisplayName("Test that a thriving animal can be added to the database")
  public void add_addsEndangeredAnimal_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    assertTrue(animalDao.getAll().contains(thrivingAnimal));
  }

  @Test
  @DisplayName("Test that id is set when a thriving animal is added to the database")
  public void add_addsEndangeredAnimalSetsId_true(ThrivingAnimal thrivingAnimal) {
    int initialId = thrivingAnimal.getId();
    animalDao.add(thrivingAnimal);
    assertNotEquals(initialId, thrivingAnimal.getId());
  }

  @Test
  @DisplayName("Test that all added thriving animals can be retrieved from the database")
  public void getAll_returnsAddedEndangeredAnimals_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    assertEquals(1, animalDao.getAll().size());
  }

  @Test
  @DisplayName("Test that empty list is returned if no records in the database")
  public void getAll_returnsEmptyListIfNoRecords_true() {
    assertEquals(0, animalDao.getAll().size());
  }



  @AfterEach
  void tearDown() {
    animalDao.deleteAll();
  }

  @AfterAll
  static void afterAll() {
    connection.close();
  }
}