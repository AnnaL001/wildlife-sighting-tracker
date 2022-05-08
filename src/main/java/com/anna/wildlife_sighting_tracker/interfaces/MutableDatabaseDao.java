package com.anna.wildlife_sighting_tracker.interfaces;

import java.util.List;

public interface MutableDatabaseDao<T> {
  // CREATE
  void add(T data);

  // READ
  List<T> getAll();
  T get(int id);

  // UPDATE
  void update(T data);

  // DELETE
  void delete(int id);
  void deleteAll();
}
