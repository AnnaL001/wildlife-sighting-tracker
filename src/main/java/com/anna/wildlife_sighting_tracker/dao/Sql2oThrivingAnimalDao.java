package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.interfaces.DatabaseDao;
import com.anna.wildlife_sighting_tracker.models.ThrivingAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oThrivingAnimalDao implements DatabaseDao<ThrivingAnimal> {
  private final Sql2o sql2o;

  public Sql2oThrivingAnimalDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public void add(ThrivingAnimal data) {
    String insertQuery = "INSERT INTO animals (image, name, speciesid, category) VALUES (:image, :name, :speciesId, :category)";
    try(Connection connection = sql2o.open()){
      int id = (int) connection.createQuery(insertQuery, true)
              .addParameter("image", data.getImage())
              .addParameter("name", data.getName())
              .addParameter("speciesId", data.getSpeciesId())
              .addParameter("category", data.getCategory())
              .executeUpdate()
              .getKey();
      data.setId(id);
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  @Override
  public List<ThrivingAnimal> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE category = 'Thriving'")
              .throwOnMappingFailure(false)
              .executeAndFetch(ThrivingAnimal.class); //fetch a list of thriving animals
    }
  }

  @Override
  public ThrivingAnimal get(int id) {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE id = :id AND category = 'Thriving'")
              .addParameter("id", id)
              .throwOnMappingFailure(false)
              .executeAndFetchFirst(ThrivingAnimal.class); //fetch an individual thriving animal
    }
  }

  @Override
  public void update(ThrivingAnimal data) {
    String updateQuery = "UPDATE animals SET (image, name, speciesid) = (:image, :name, :speciesId) WHERE id=:id";
    try(Connection connection = sql2o.open()){
      connection.createQuery(updateQuery)
              .addParameter("image", data.getImage())
              .addParameter("name", data.getName())
              .addParameter("speciesId", data.getSpeciesId())
              .addParameter("id", data.getId())
              .executeUpdate();
    } catch (Sql2oException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void delete(int id) {
    String deleteQuery = "DELETE from animals WHERE id=:id AND category = 'Thriving'";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(deleteQuery)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  @Override
  public void deleteAll() {
    String deleteQuery = "DELETE from animals WHERE category = 'Thriving'";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(deleteQuery)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }
}
