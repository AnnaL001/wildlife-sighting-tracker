package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.interfaces.ImmutableDatabaseDao;
import com.anna.wildlife_sighting_tracker.models.Location;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oLocationDao implements ImmutableDatabaseDao<Location> {
  private final Sql2o sql2o;

  public Sql2oLocationDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  /**
   * Function to retrieve list of locations' data from the database
   */
  @Override
  public List<Location> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM locations")
              .executeAndFetch(Location.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Function to retrieve a location's data from the database
   * @param id A location's id
   */
  @Override
  public Location get(int id) {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM locations WHERE id=:id")
              .addParameter("id", id)
              .executeAndFetchFirst(Location.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }
}
