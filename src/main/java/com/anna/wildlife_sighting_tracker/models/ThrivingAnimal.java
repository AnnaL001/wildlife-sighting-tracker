package com.anna.wildlife_sighting_tracker.models;

import com.anna.wildlife_sighting_tracker.base.Animal;

public class ThrivingAnimal extends Animal {
  public static final String ANIMAL_CATEGORY = "Thriving";

  public ThrivingAnimal(String imageUrl, String name, int speciesId) {
    this.imageUrl = imageUrl;
    this.name = name;
    this.speciesId = speciesId;
    this.category = ANIMAL_CATEGORY;
  }
}
