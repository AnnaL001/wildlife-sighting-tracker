package com.anna.wildlife_sighting_tracker.base;

import java.util.Objects;

public abstract class Animal {
  protected int id;
  protected String imageUrl;
  protected String name;
  protected int speciesId;
  protected String category;

  public int getId() {
    return id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getName() {
    return name;
  }

  public int getSpeciesId() {
    return speciesId;
  }

  public String getCategory() {
    return category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Animal animal = (Animal) o;
    return id == animal.id && speciesId == animal.speciesId && Objects.equals(imageUrl, animal.imageUrl) && Objects.equals(name, animal.name) && Objects.equals(category, animal.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imageUrl, name, speciesId, category);
  }
}
