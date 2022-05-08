package com.anna.wildlife_sighting_tracker.models;

public class Ranger {
  private int id;
  private final String name;
  private final String badge;
  private final int phone;
  private final String email;

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


}
