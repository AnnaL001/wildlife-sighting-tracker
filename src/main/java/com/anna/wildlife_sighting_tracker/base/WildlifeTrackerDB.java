package com.anna.wildlife_sighting_tracker.base;

import org.sql2o.Sql2o;

public class WildlifeTrackerDB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "anna", "SurMaRoute01$");
}
