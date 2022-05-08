package com.anna.wildlife_sighting_tracker.base;

import java.util.Objects;

public abstract class Animal {
  protected int id;
  protected String image;
  protected String name;
  protected int speciesId;
  protected String category;

  public int getId() {
    return id;
  }

  public String getImage() {
    return image;
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

  public void setId(int id) {
    this.id = id;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSpeciesId(int speciesId) {
    this.speciesId = speciesId;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Animal animal = (Animal) o;
    return speciesId == animal.speciesId && Objects.equals(image, animal.image) && Objects.equals(name, animal.name) && Objects.equals(category, animal.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, name, speciesId, category);
  }
}
