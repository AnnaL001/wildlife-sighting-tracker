package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oEndangeredAnimalDao implements AnimalDao<EndangeredAnimal> {
  private final Sql2o sql2o;

  public Sql2oEndangeredAnimalDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public void add(EndangeredAnimal animal) {
    String insertQuery = "INSERT INTO animals (image, name, speciesid, health, age, category) VALUES (:image, :name, :speciesId, :health, :age, :category)";
    try(Connection connection = sql2o.open()){
      int id = (int) connection.createQuery(insertQuery, true)
              .addParameter("image", animal.getImage())
              .addParameter("name", animal.getName())
              .addParameter("speciesId", animal.getSpeciesId())
              .addParameter("health", animal.getHealth())
              .addParameter("age", animal.getAge())
              .addParameter("category", animal.getCategory())
              .executeUpdate()
              .getKey();
      animal.setId(id);
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  @Override
  public List<EndangeredAnimal> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE category = 'Endangered'")
              .throwOnMappingFailure(false)
              .executeAndFetch(EndangeredAnimal.class); //fetch a list of endangered animals
    }
  }

  @Override
  public EndangeredAnimal get(int id) {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE id = :id AND category = 'Endangered'")
              .addParameter("id", id)
              .executeAndFetchFirst(EndangeredAnimal.class); //fetch an individual endangered animal
    }
  }

  @Override
  public void update(EndangeredAnimal animal) {
    String updateQuery = "UPDATE tasks SET (image, name, speciesid, health, age category) = (:image, :name, :speciesId, :health, :age, :category) WHERE id=:id";
    try(Connection connection = sql2o.open()){
      connection.createQuery(updateQuery)
              .addParameter("image", animal.getImage())
              .addParameter("name", animal.getName())
              .addParameter("speciesId", animal.getSpeciesId())
              .addParameter("health", animal.getHealth())
              .addParameter("age", animal.getAge())
              .addParameter("category", animal.getCategory())
              .addParameter("id", animal.getId())
              .executeUpdate();
    } catch (Sql2oException exception) {
      exception.printStackTrace();
    }
  }

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
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE from animals";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(sql)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }
}
