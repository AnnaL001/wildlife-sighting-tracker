package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import com.anna.wildlife_sighting_tracker.models.Sighting;
import com.anna.wildlife_sighting_tracker.parameter_resolver.EndangeredAnimalParameterResolver;
import com.anna.wildlife_sighting_tracker.parameter_resolver.SightingParameterResolver;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SightingParameterResolver.class)
@ExtendWith(EndangeredAnimalParameterResolver.class)
class Sql2oSightingDaoTest {
  private static Sql2oSightingDao sightingDao;
  private static Sql2oEndangeredAnimalDao animalDao;
  private static Connection connection;

  @BeforeAll
  static void beforeAll() {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "anna", "SurMaRoute01$");
    sightingDao = new Sql2oSightingDao(sql2o);
    animalDao = new Sql2oEndangeredAnimalDao(sql2o);
    connection = sql2o.open();
  }

  @Test
  @DisplayName("Test that a sighting can be added to database")
  public void add_addsSighting_true(Sighting sighting) {
    sightingDao.add(sighting);
    assertTrue(sightingDao.getAll().contains(sighting));
  }

  @Test
  @DisplayName("Test that a sighting's id is set when inserted into the database")
  public void add_addsSightingSetsSightingId_true(Sighting sighting) {
    int initialId = sighting.getId();
    sightingDao.add(sighting);
    assertNotEquals(initialId, sighting.getId());
  }

  @Test
  @DisplayName("Test that a sighting's reported time is recorded in the database")
  public void add_recordsTimeOfCreationInDatabase(Sighting sighting) {
    sightingDao.add(sighting);
    DateTimeZone zone = DateTimeZone.forID("Africa/Nairobi");
    Timestamp savedReportedTime = sightingDao.get(sighting.getId()).getReportedAt();
    LocalDate reportedDate = new LocalDate(savedReportedTime, zone);
    Timestamp rightNow = new Timestamp(new Date().getTime());
    LocalDate currentDate = new LocalDate(rightNow, zone);
    assertEquals(currentDate.dayOfYear(), reportedDate.dayOfYear());
  }

  @Test
  @DisplayName("Test that a list of all sightings can be retrieved from the database")
  public void getAll_returnsListOfSightings_true(Sighting sighting) {
    sightingDao.add(sighting);
    sightingDao.add(setUpSighting());
    assertEquals(2, sightingDao.getAll().size());
  }

  @Test
  @DisplayName("Test that empty list is returned if no sightings recorded in database")
  public void getAll_returnsEmptyListIfNoRecords_true() {
    assertEquals(0, sightingDao.getAll().size());
  }

  @Test
  @DisplayName("Test that a sighting's data can be updated")
  public void update_updatesDataOfSighting_true(Sighting sighting) {
    sightingDao.add(sighting);
    sighting.setLocationId(2);
    sighting.setRangerId(2);
    sightingDao.update(sighting);
    Sighting foundSighting = sightingDao.get(sighting.getId());
    assertEquals(2, foundSighting.getLocationId());
    assertEquals(2, foundSighting.getRangerId());
  }

  @Test
  @DisplayName("Test that a sighting's data can be deleted")
  public void delete_deletesASighting_true(Sighting sighting) {
    sightingDao.add(sighting);
    sightingDao.delete(sighting.getId());
    assertFalse(sightingDao.getAll().contains(sighting));
  }

  @Test
  @DisplayName("Test that all sightings' data can be deleted")
  public void deleteAll_deletesAllSightings_true(Sighting sighting) {
    sightingDao.add(sighting);
    sightingDao.add(setUpSighting());
    sightingDao.deleteAll();
    assertEquals(0, sightingDao.getAll().size());
  }

  @Test
  public void addSightingToAnimal_addsAnimalSighted(Sighting sighting, EndangeredAnimal endangeredAnimal) {
    animalDao.add(endangeredAnimal);
    sightingDao.add(sighting);
    sightingDao.addAnimalToSighting(sighting.getId(), endangeredAnimal.getId());
    assertTrue(sightingDao.getAnimals(sighting.getId()).contains(endangeredAnimal));
  }

  private Sighting setUpSighting(){
    return new Sighting(2,1);
  }

  @AfterEach
  public void tearDown() {
    sightingDao.deleteAll();
    animalDao.deleteAll();
  }

  @AfterAll
  static void afterAll() {
    connection.close();
  }
}