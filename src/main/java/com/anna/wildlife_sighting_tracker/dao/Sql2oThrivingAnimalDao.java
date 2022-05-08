package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.interfaces.AnimalDao;
import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import com.anna.wildlife_sighting_tracker.models.ThrivingAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oThrivingAnimalDao implements AnimalDao<ThrivingAnimal> {
  private final Sql2o sql2o;

  public Sql2oThrivingAnimalDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public void add(ThrivingAnimal animal) {
    String insertQuery = "INSERT INTO animals (image, name, speciesid, category) VALUES (:image, :name, :speciesId, :category)";
    try(Connection connection = sql2o.open()){
      int id = (int) connection.createQuery(insertQuery, true)
              .addParameter("image", animal.getImage())
              .addParameter("name", animal.getName())
              .addParameter("speciesId", animal.getSpeciesId())
              .addParameter("category", animal.getCategory())
              .executeUpdate()
              .getKey();
      animal.setId(id);
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  @Override
  public List<ThrivingAnimal> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM animals WHERE category = 'Thriving'")
              .throwOnMappingFailure(false)
              .executeAndFetch(ThrivingAnimal.class); //fetch a list of endangered animals
    }
  }

  @Override
  public ThrivingAnimal get(int id) {
    return null;
  }

  @Override
  public void update(ThrivingAnimal animal) {

  }

  @Override
  public void delete(int id) {

  }

  @Override
  public void deleteAll() {
    String sql = "DELETE from animals WHERE category = 'Thriving'";
    try (Connection connection = sql2o.open()) {
      connection.createQuery(sql)
              .executeUpdate();
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }
}
