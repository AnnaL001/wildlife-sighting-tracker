package com.anna.wildlife_sighting_tracker.models;

import com.anna.wildlife_sighting_tracker.base.Animal;

public class EndangeredAnimal extends Animal {
  private String healthStatus;
  private String age;
  public static final String ANIMAL_CATEGORY = "Endangered";
  public static final String HEALTHY = "Healthy";
  public static final String ILL = "Ill";
  public static final String OKAY = "Okay";
  public static final String NEWBORN = "Newborn";
  public static final String YOUNG = "Young";
  public static final String ADULT = "Adult";

  public EndangeredAnimal(String imageUrl, String name, int speciesId, String healthStatus, String age) {
    this.imageUrl = imageUrl;
    this.name = name;
    this.speciesId = speciesId;
    this.healthStatus = healthStatus;
    this.age = age;
    this.category = ANIMAL_CATEGORY;
  }

  public String getHealthStatus() {
    return healthStatus;
  }

  public String getAge() {
    return age;
  }
}
