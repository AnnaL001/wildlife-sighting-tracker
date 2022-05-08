package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.interfaces.ImmutableDatabaseDao;
import com.anna.wildlife_sighting_tracker.models.Ranger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oRangerDao implements ImmutableDatabaseDao<Ranger> {
  private final Sql2o sql2o;

  public Sql2oRangerDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public List<Ranger> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM rangers")
              .executeAndFetch(Ranger.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

  @Override
  public Ranger get(int id) {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM rangers WHERE id=:id")
              .addParameter("id", id)
              .executeAndFetchFirst(Ranger.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }
}
