package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.base.Animal;
import com.anna.wildlife_sighting_tracker.interfaces.DatabaseDao;
import com.anna.wildlife_sighting_tracker.models.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSightingDao implements DatabaseDao<Sighting> {
  private final Sql2o sql2o;

  public Sql2oSightingDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public void add(Sighting data) {
    String insertQuery = "INSERT INTO sightings (locationid, rangerid, reportedat) VALUES (:locationId, :rangerId, :reportedAt)";
    try(Connection connection = sql2o.open()){
      int id = (int) connection.createQuery(insertQuery, true)
              .addParameter("locationId", data.getLocationId())
              .addParameter("rangerId", data.getRangerId())
              .addParameter("reportedAt", data.getReportedAt())
              .executeUpdate()
              .getKey();
      data.setId(id);
    } catch (Sql2oException exception){
      exception.printStackTrace();
    }
  }

  @Override
  public List<Sighting> getAll() {
    try(Connection connection = sql2o.open()){
      return connection.createQuery("SELECT * FROM sightings")
              .executeAndFetch(Sighting.class);
    } catch (Sql2oException exception){
      exception.printStackTrace();
      return null;
    }
  }

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

  public void addAnimalToSighting(Sighting sighting, Animal animal){
    try(Connection connection = sql2o.open()) {
      String sql = "INSERT INTO animals_sightings (animalid, sightingId) VALUES (:animalId, :sightingId)";
      connection.createQuery(sql)
              .addParameter("animalId", animal.getId())
              .addParameter("sightingId", sighting.getId())
              .executeUpdate();
    }
  }

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
  }

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
