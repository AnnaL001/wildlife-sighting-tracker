package com.anna.wildlife_sighting_tracker.base;

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
}
