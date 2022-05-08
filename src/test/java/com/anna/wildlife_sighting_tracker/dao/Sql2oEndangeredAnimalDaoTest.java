package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import com.anna.wildlife_sighting_tracker.parameter_resolver.EndangeredAnimalParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EndangeredAnimalParameterResolver.class)
class Sql2oEndangeredAnimalDaoTest {
  private static Sql2oEndangeredAnimalDao animalDao;
  private static Connection connection;
  @BeforeAll
  public static void beforeAll() {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    animalDao = new Sql2oEndangeredAnimalDao(sql2o);
    connection = sql2o.open();
  }

  @Test
  @DisplayName("Test that an endangered animal is added to database")
  public void add_addsEndangeredAnimal_true(EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    assertTrue(animalDao.getAll().contains(endangeredAnimal));
  }

  @Test
  @DisplayName("Test that id is set when an endangered animal is added to the database")
  public void add_addsEndangeredAnimalSetsId_true(EndangeredAnimal endangeredAnimal) {
    int initialId = endangeredAnimal.getId();
    animalDao.add(endangeredAnimal);
    assertNotEquals(initialId, endangeredAnimal.getId());
  }



  @AfterEach
  public void tearDown() {
    animalDao.deleteAll();
  }

  @AfterAll
  public static void afterAll() {
    connection.close();
  }
}