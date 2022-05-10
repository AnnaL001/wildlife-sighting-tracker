package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.base.Animal;
import com.anna.wildlife_sighting_tracker.interfaces.MutableDatabaseDao;
import com.anna.wildlife_sighting_tracker.models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSightingDao implements MutableDatabaseDao<Sighting> {
  private final Sql2o sql2o;

  public Sql2oSightingDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  /**
   * Function to insert sighting data into database
   * @param data Sighting class instance
   */
  @Override
  public void add(Sighting data) {
    String insertQuery = "INSERT INTO sightings (locationid, rangerid, reportedat) VALUES (:locationId, :rangerId, now())";
    try(Connection connection = sql2o.open()){
      int id = (int) connection.createQuery(insertQuery, true)
              .addParameter("locationId", data.getLocationId())
              .addParameter("rangerId", data.getRangerId())
              .executeUpdate()
              .getKey();
      data.setId(id);
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  /**
   * Function to retrieve list of sightings' data from the database
   */
  @Override
  public List<Sighting> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM sightings ORDER BY reportedat DESC")
              .executeAndFetch(Sighting.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Function to retrieve a sighting's data from the database
   * @param id A sighting's id
   */
  @Override
  public Sighting get(int id) {
    try(Connection connection = sql2o.open()) {
      return connection.createQuery("SELECT * FROM sightings WHERE id=:id")
              .addParameter("id", id)
              .executeAndFetchFirst(Sighting.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Function to update a sighting's data
   * @param data Sighting class instance
   */
  @Override
  public void update(Sighting data) {
    String updateQuery = "UPDATE sightings SET (locationid, rangerid, reportedat) = (:locationId, :rangerId, :reportedAt) WHERE id=:id";
    try(Connection connection = sql2o.open()){
      connection.createQuery(updateQuery)
              .addParameter("locationId", data.getLocationId())
              .addParameter("rangerId", data.getRangerId())
              .addParameter("reportedAt", data.getReportedAt())
              .addParameter("id", data.getId())
              .executeUpdate();
    } catch (Sql2oException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Function to add animals sighted in a location by a ranger
   * @param animalId An animal's id
   * @param sightingId A sighting's id
   */
  public void addAnimalToSighting(int sightingId, int animalId){
    try(Connection connection = sql2o.open()) {
      String insertQuery = "INSERT INTO animals_sightings (animalid, sightingId) VALUES (:animalId, :sightingId)";
      connection.createQuery(insertQuery)
              .addParameter("animalId", animalId)
              .addParameter("sightingId", sightingId)
              .executeUpdate();
    }
  }

  /**
   * Function to update animals sighted in a location by a ranger
   * @param animalId An animal's id
   * @param sightingId A sighting's id
   */
  public void removeAnimalFromSighting(int sightingId, int animalId){
    try(Connection connection = sql2o.open()){
      String deleteQuery = "DELETE FROM animals_sightings WHERE animalid=:animalId AND sightingid = :sightingId";
      connection.createQuery(deleteQuery)
              .addParameter("animalId", animalId)
              .addParameter("sightingId", sightingId)
              .executeUpdate();
    }
  }

  /**
   * Function to retrieve sightings based on a ranger
   * @param rangerId A ranger's id
   */
  public List<Sighting> getSightingsByRangers(int rangerId){
    String selectQuery = "SELECT * FROM sightings WHERE rangerid = :rangerId";
    try(Connection connection = sql2o.open()){
      return connection.createQuery(selectQuery)
              .addParameter("rangerId", rangerId)
              .executeAndFetch(Sighting.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Function to retrieve sightings based on location
   * @param locationId A location's id
   */
  public List<Sighting> getSightingsByLocations(int locationId){
    String selectQuery = "SELECT * FROM sightings WHERE locationid = :locationId";
    try(Connection connection = sql2o.open()){
      return connection.createQuery(selectQuery)
              .addParameter("locationId", locationId)
              .executeAndFetch(Sighting.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Function to retrieve a list of animals sighted
   * @param id A sighting's id
   */
  public List<Animal> getAnimals(int id){
    try(Connection connection = sql2o.open()){
      List<Animal> animals = new ArrayList<>();

      String selectQuery = "SELECT animals.* FROM animals_sightings JOIN sightings ON (animals_sightings.sightingid = sightings.id)" +
              "JOIN animals ON (animals_sightings.animalid = animals.id) WHERE sightings.id = :sightingId AND animals.category = 'Endangered'";
      List<EndangeredAnimal> endangeredAnimals = connection.createQuery(selectQuery)
              .addParameter("sightingId", id)
              .throwOnMappingFailure(false)
              .executeAndFetch(EndangeredAnimal.class);

      String selectQuery2 = "SELECT animals.* FROM animals_sightings JOIN sightings ON (animals_sightings.sightingid = sightings.id)" +
              "JOIN animals ON (animals_sightings.animalid = animals.id) WHERE sightings.id = :sightingId AND animals.category = 'Thriving'";

      List<ThrivingAnimal> thrivingAnimals = connection.createQuery(selectQuery2)
              .addParameter("sightingId", id)
              .throwOnMappingFailure(false)
              .executeAndFetch(ThrivingAnimal.class);

      animals.addAll(endangeredAnimals);
      animals.addAll(thrivingAnimals);
      return animals;
    }
  }

  /**
   * Function to delete a sighting's data
   * @param id A sighting's id
   */
  @Override
  public void delete(int id) {
    String deleteQuery = "DELETE from sightings WHERE id=:id";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(deleteQuery)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }

    String anotherDeleteQuery = "DELETE FROM animals_sightings WHERE sightingid = :sightingId";
    try(Connection connection = sql2o.open()) {
      connection.createQuery(anotherDeleteQuery)
              .addParameter("sightingId", id)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  /**
   * Function to clear all sightings' data from the database
   */
  @Override
  public void deleteAll() {
    String deleteQuery = "DELETE from sightings";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(deleteQuery)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }
}
