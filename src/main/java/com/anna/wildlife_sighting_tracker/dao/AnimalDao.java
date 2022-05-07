package com.anna.wildlife_sighting_tracker.dao;

import com.anna.wildlife_sighting_tracker.base.Animal;

import java.util.List;

public interface AnimalDao {
  // CREATE
  void add(Animal animal);

  // READ
  List<Animal> getAll();
  Animal get(int id);

  // UPDATE
  void update(Animal animal);

  // DELETE
  void delete(int id);
  void deleteAll();
}
