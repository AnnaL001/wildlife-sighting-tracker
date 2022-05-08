package com.anna.wildlife_sighting_tracker.models;

import java.util.Objects;

public class Species {
  private int id;
  private final String name;

  public Species(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Species species = (Species) o;
    return Objects.equals(name, species.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
