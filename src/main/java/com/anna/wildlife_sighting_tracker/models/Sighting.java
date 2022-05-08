package com.anna.wildlife_sighting_tracker.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Sighting {
  private int id;
  private int locationId;
  private int rangerId;
  private Timestamp reportedAt;

  public Sighting(int locationId, int rangerId, Timestamp reportedAt) {
    this.locationId = locationId;
    this.rangerId = rangerId;
    this.reportedAt = reportedAt;
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
    return locationId == sighting.locationId && rangerId == sighting.rangerId && Objects.equals(reportedAt, sighting.reportedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationId, rangerId, reportedAt);
  }
}
