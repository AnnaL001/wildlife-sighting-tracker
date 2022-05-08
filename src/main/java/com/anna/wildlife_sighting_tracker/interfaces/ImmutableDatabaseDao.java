package com.anna.wildlife_sighting_tracker.interfaces;

import java.util.List;

public interface ImmutableDatabaseDao<T> {
  // READ
  List<T> getAll();
  T get(int id);
}
