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
  public void add_addsThrivingAnimal_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    assertTrue(animalDao.getAll().contains(thrivingAnimal));
  }

  @Test
  @DisplayName("Test that id is set when a thriving animal is added to the database")
  public void add_addsThrivingAnimalAndSetsId_true(ThrivingAnimal thrivingAnimal) {
    int initialId = thrivingAnimal.getId();
    animalDao.add(thrivingAnimal);
    assertNotEquals(initialId, thrivingAnimal.getId());
  }

  @Test
  @DisplayName("Test that all added thriving animals can be retrieved from the database")
  public void getAll_returnsAddedThrivingAnimals_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    assertEquals(1, animalDao.getAll().size());
  }

  @Test
  @DisplayName("Test that empty list is returned if no records in the database")
  public void getAll_returnsEmptyListIfNoRecords_true() {
    assertEquals(0, animalDao.getAll().size());
  }

  @Test
  @DisplayName("Test that a thriving animal can be retrieved from the database")
  public void get_returnsThrivingAnimalWithSameId_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    assertEquals(thrivingAnimal, animalDao.get(thrivingAnimal.getId()));
  }

  @Test
  @DisplayName("Test that a thriving animal's data can be updated")
  public void update_updatesDataOfThrivingAnimal_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    thrivingAnimal.setImage("https://wildlife_tracker/lion.jpg");
    thrivingAnimal.setName("Cecil");
    thrivingAnimal.setSpeciesId(2);
    animalDao.update(thrivingAnimal);
    ThrivingAnimal foundAnimal = animalDao.get(thrivingAnimal.getId());
    assertEquals("https://wildlife_tracker/lion.jpg", foundAnimal.getImage());
    assertEquals("Cecil", foundAnimal.getName());
    assertEquals(2, foundAnimal.getSpeciesId());
  }

  @Test
  @DisplayName("Test that a thriving animal's data can be deleted")
  public void delete_deletesAThrivingAnimal_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    animalDao.delete(thrivingAnimal.getId());
    assertFalse(animalDao.getAll().contains(thrivingAnimal));
  }

  @Test
  @DisplayName("Test that all thriving animals' data can be deleted")
  public void deleteAll_deletesAllThrivingAnimals_true(ThrivingAnimal thrivingAnimal) {
    animalDao.add(thrivingAnimal);
    animalDao.add(setUpThrivingAnimal());
    animalDao.deleteAll();
    assertEquals(0, animalDao.getAll().size());
  }

  private ThrivingAnimal setUpThrivingAnimal(){
    return new ThrivingAnimal(
            "https://upload.wikimedia.org/wikipedia/commons/1/1e/Cecil_the_lion_at_Hwange_National_Park_%284516560206%29.jpg",
            "Poppy",
            1
    );
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