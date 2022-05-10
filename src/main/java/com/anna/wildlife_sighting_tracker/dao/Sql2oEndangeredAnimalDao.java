package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.interfaces.MutableDatabaseDao;
import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oEndangeredAnimalDao implements MutableDatabaseDao<EndangeredAnimal> {
  private final Sql2o sql2o;

  public Sql2oEndangeredAnimalDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  /**
   * Function to insert endangered animal data into database
   * @param data EndangeredAnimal instance
   */
  @Override
  public void add(EndangeredAnimal data) {
    String insertQuery = "INSERT INTO animals (image, name, speciesid, health, age, category) VALUES (:image, :name, :speciesId, :health, :age, :category)";
    try(Connection connection = sql2o.open()){
      int id = (int) connection.createQuery(insertQuery, true)
              .addParameter("image", data.getImage())
              .addParameter("name", data.getName())
              .addParameter("speciesId", data.getSpeciesId())
              .addParameter("health", data.getHealth())
              .addParameter("age", data.getAge())
              .addParameter("category", data.getCategory())
              .executeUpdate()
              .getKey();
      data.setId(id);
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  /**
   * Function to retrieve list of endangered animals' data from the database
   */
  @Override
  public List<EndangeredAnimal> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE category = 'Endangered'")
              .throwOnMappingFailure(false)
              .executeAndFetch(EndangeredAnimal.class); //fetch a list of endangered animals
    }
  }

  /**
   * Function to retrieve an endangered animal's data from the database
   * @param id An endangered animal's id
   */
  @Override
  public EndangeredAnimal get(int id) {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE id = :id AND category = 'Endangered'")
              .addParameter("id", id)
              .throwOnMappingFailure(false)
              .executeAndFetchFirst(EndangeredAnimal.class); //fetch an individual endangered animal
    }
  }

  /**
   * Function to update an endangered animal's data
   * @param data EndangeredAnimal instance
   */
  @Override
  public void update(EndangeredAnimal data) {
    String updateQuery = "UPDATE animals SET (image, name, speciesid, health, age) = (:image, :name, :speciesId, :health, :age) WHERE id=:id";
    try(Connection connection = sql2o.open()){
      connection.createQuery(updateQuery)
              .addParameter("image", data.getImage())
              .addParameter("name", data.getName())
              .addParameter("speciesId", data.getSpeciesId())
              .addParameter("health", data.getHealth())
              .addParameter("age", data.getAge())
              .addParameter("id", data.getId())
              .executeUpdate();
    } catch (Sql2oException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Function to delete an endangered animal's data
   * @param id An endangered animal's id
   */
  @Override
  public void delete(int id) {
    String deleteQuery = "DELETE from animals WHERE id=:id AND category = 'Endangered'";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(deleteQuery)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }

    String anotherDeleteQuery = "DELETE FROM animals_sightings WHERE animalid = :animalId";
    try(Connection connection = sql2o.open()) {
      connection.createQuery(anotherDeleteQuery)
              .addParameter("animalId", id)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  /**
   * Function to clear all endangered animals' data from the database
   */
  @Override
  public void deleteAll() {
    String deleteQuery = "DELETE from animals WHERE category = 'Endangered'";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(deleteQuery)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }
}
