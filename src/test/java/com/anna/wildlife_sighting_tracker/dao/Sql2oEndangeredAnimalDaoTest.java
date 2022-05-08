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

  @Test
  @DisplayName("Test that all added endangered animals can be retrieved from the database")
  public void getAll_returnsAddedEndangeredAnimals_true(EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    assertEquals(1, animalDao.getAll().size());
  }

  @Test
  @DisplayName("Test that empty list is returned if no records in the database")
  public void getAll_returnsEmptyListIfNoRecords_true() {
    assertEquals(0, animalDao.getAll().size());
  }

  @Test
  @DisplayName("Test that an endangered animal can be retrieved from the database")
  public void get_returnsEndangeredAnimalWithSameId_true(EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    assertEquals(endangeredAnimal, animalDao.get(endangeredAnimal.getId()));
  }

  @Test
  @DisplayName("Test that an endangered animal's data can be updated")
  public void update_updatesDataOfEndangeredAnimal_true(EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    endangeredAnimal.setImage("https://wildlife_tracker/rhino.jpg");
    endangeredAnimal.setName("Cecil");
    endangeredAnimal.setSpeciesId(2);
    endangeredAnimal.setHealth(EndangeredAnimal.ILL);
    endangeredAnimal.setAge(EndangeredAnimal.NEWBORN);
    animalDao.update(endangeredAnimal);
    EndangeredAnimal foundAnimal = animalDao.get(endangeredAnimal.getId());
    assertEquals("https://wildlife_tracker/rhino.jpg", foundAnimal.getImage());
    assertEquals("Cecil", foundAnimal.getName());
    assertEquals(2, foundAnimal.getSpeciesId());
    assertEquals(EndangeredAnimal.ILL, foundAnimal.getHealth());
    assertEquals(EndangeredAnimal.NEWBORN, foundAnimal.getAge());
  }

  @Test
  @DisplayName("Test that an endangered animal's data can be deleted")
  public void delete_deletesAnEndangeredAnimal_true(EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    animalDao.delete(endangeredAnimal.getId());
    assertFalse(animalDao.getAll().contains(endangeredAnimal));
  }

  @Test
  @DisplayName("Test that all endangered animals' data can be deleted")
  public void deleteAll_deletesAllThrivingAnimals_true(EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    animalDao.add(setUpEndangeredAnimal());
    animalDao.deleteAll();
    assertEquals(0, animalDao.getAll().size());
  }

  private EndangeredAnimal setUpEndangeredAnimal(){
    return new EndangeredAnimal(
            "https://upload.wikimedia.org/wikipedia/commons/1/1e/Cecil_the_lion_at_Hwange_National_Park_%284516560206%29.jpg",
            "Poppy",
            1,
            EndangeredAnimal.HEALTHY,
            EndangeredAnimal.NEWBORN
    );
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