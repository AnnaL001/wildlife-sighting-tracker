package com.anna.wildlife_sighting_tracker.models;

import com.anna.wildlife_sighting_tracker.base.Animal;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;
  public static final String ANIMAL_CATEGORY = "Endangered";
  public static final String HEALTHY = "Healthy";
  public static final String ILL = "Ill";
  public static final String OKAY = "Okay";
  public static final String NEWBORN = "Newborn";
  public static final String YOUNG = "Young";
  public static final String ADULT = "Adult";

  public EndangeredAnimal(String image, String name, int speciesId, String health, String age) {
    this.image = image;
    this.name = name;
    this.speciesId = speciesId;
    this.health = health;
    this.age = age;
    this.category = ANIMAL_CATEGORY;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public void setHealth(String health) {
    this.health = health;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
