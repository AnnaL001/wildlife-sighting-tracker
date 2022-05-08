package com.anna.wildlife_sighting_tracker.dao;

import java.util.List;

public interface AnimalDao<T> {
  // CREATE
  void add(T animal);

  // READ
  List<T> getAll();
  T get(int id);

  // UPDATE
  void update(T animal);

  // DELETE
  void delete(int id);
  void deleteAll();
}
