package com.anna.wildlife_sighting_tracker.models;

import org.joda.time.LocalDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sighting {
  private int id;
  private int locationId;
  private int rangerId;
  private Timestamp reportedAt;

  private String formattedReportedDate;


  public Sighting(int locationId, int rangerId) {
    this.locationId = locationId;
    this.rangerId = rangerId;
  }

  public int getId() {
    return id;
  }

  public int getLocationId() {
    return locationId;
  }

  public int getRangerId() {
    return rangerId;
  }

  public Timestamp getReportedAt() {
    return reportedAt;
  }

  public String getFormattedReportedDate() {
    return formattedReportedDate;
  }

  public void setFormattedReportedDate(String formattedReportedDate) {
    this.formattedReportedDate = formattedReportedDate;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setLocationId(int locationId) {
    this.locationId = locationId;
  }

  public void setRangerId(int rangerId) {
    this.rangerId = rangerId;
  }

  public void setReportedAt(Timestamp reportedAt) {
    this.reportedAt = reportedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sighting sighting = (Sighting) o;
    return locationId == sighting.locationId && rangerId == sighting.rangerId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationId, rangerId);
  }
}
