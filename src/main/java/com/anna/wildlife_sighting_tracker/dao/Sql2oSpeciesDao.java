package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.interfaces.ImmutableDatabaseDao;
import com.anna.wildlife_sighting_tracker.models.Species;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSpeciesDao implements ImmutableDatabaseDao<Species> {
  private final Sql2o sql2o;

  public Sql2oSpeciesDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  /**
   * Function to retrieve list of species' data from the database
   */
  @Override
  public List<Species> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM species")
              .executeAndFetch(Species.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Function to retrieve a species' data from the database
   * @param id A species' id
   */
  @Override
  public Species get(int id) {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM species WHERE id=:id")
              .addParameter("id", id)
              .executeAndFetchFirst(Species.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }
}
