package com.anna.wildlife_sighting_tracker;

import com.anna.wildlife_sighting_tracker.dao.*;
import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import com.anna.wildlife_sighting_tracker.models.ThrivingAnimal;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
public class App {
  public static void main(String[] args) {
    staticFileLocation("public/");
    Map<String, Object> model = new HashMap<>();
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "anna", "SurMaRoute01$");
    Sql2oEndangeredAnimalDao endangeredAnimalDao = new Sql2oEndangeredAnimalDao(sql2o);
    Sql2oThrivingAnimalDao thrivingAnimalDao = new Sql2oThrivingAnimalDao(sql2o);
    Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);
    Sql2oRangerDao rangerDao = new Sql2oRangerDao(sql2o);
    Sql2oSpeciesDao speciesDao = new Sql2oSpeciesDao(sql2o);

    get("/", (request, response) -> new HandlebarsTemplateEngine().render(
            new ModelAndView(new HashMap<>(), "index.hbs")
    ));

    get("/home", (request, response) -> {
      model.put("animals_count", endangeredAnimalDao.getAll().size() + thrivingAnimalDao.getAll().size());
      model.put("sightings_count", sightingDao.getAll().size());
      model.put("rangers_count", rangerDao.getAll().size());
      model.put("species_count", speciesDao.getAll().size());
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "home.hbs")
      );
    });

    get("/animals", (request, response) -> {
      model.put("endangered", endangeredAnimalDao.getAll());
      model.put("thriving", thrivingAnimalDao.getAll());
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "animals.hbs")
      );
    });

    get("/animals/:id", (request, response) -> {
      ThrivingAnimal thrivingAnimal = thrivingAnimalDao.get(parseInt(request.params("id")));
      EndangeredAnimal endangeredAnimal = endangeredAnimalDao.get(parseInt(request.params("id")));
      model.put("thriving", thrivingAnimal);
      model.put("endangered", endangeredAnimal);
      if(thrivingAnimal != null){
        model.put("species", speciesDao.get(thrivingAnimal.getSpeciesId()));
      }
      if(endangeredAnimal != null){
        model.put("species", speciesDao.get(endangeredAnimal.getSpeciesId()));
      }
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "animal.hbs")
      );
    });
  }
}