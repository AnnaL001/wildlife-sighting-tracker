package com.anna.wildlife_sighting_tracker.models;

import java.util.List;
import java.util.Objects;

public class Ranger {
  private int id;
  private final String name;
  private final String badge;
  private final int phone;
  private final String email;

  private List<Sighting> sightings;

  public Ranger(String name, String badge, int phone, String email) {
    this.name = name;
    this.badge = badge;
    this.phone = phone;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBadge() {
    return badge;
  }

  public int getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public List<Sighting> getSightings() {
    return sightings;
  }

  public void setSightings(List<Sighting> sightings) {
    this.sightings = sightings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ranger ranger = (Ranger) o;
    return phone == ranger.phone && Objects.equals(name, ranger.name) && Objects.equals(badge, ranger.badge) && Objects.equals(email, ranger.email) && Objects.equals(sightings, ranger.sightings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, badge, phone, email, sightings);
  }
}
