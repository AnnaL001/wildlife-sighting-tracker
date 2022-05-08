package com.anna.wildlife_sighting_tracker.models;

import java.util.Objects;

public class Location {
  private int id;
  private final String name;
  private final String description;

  public Location(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Location location = (Location) o;
    return Objects.equals(name, location.name) && Objects.equals(description, location.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }
}
