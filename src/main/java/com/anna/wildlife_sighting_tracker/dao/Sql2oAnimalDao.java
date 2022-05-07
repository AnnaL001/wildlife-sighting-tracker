package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.base.Animal;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oAnimalDao implements AnimalDao {
  private final Sql2o sql2o;
  public Sql2oAnimalDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public void add(Animal animal) {

  }

  @Override
  public List<Animal> getAll() {
    return null;
  }

  @Override
  public Animal get(int id) {
    return null;
  }

  @Override
  public void update(Animal animal) {

  }

  @Override
  public void delete(int id) {

  }

  @Override
  public void deleteAll() {

  }
}
