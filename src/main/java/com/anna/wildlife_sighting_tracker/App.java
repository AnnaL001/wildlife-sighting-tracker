package com.anna.wildlife_sighting_tracker;

import com.anna.wildlife_sighting_tracker.base.Animal;
import com.anna.wildlife_sighting_tracker.dao.*;
import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import com.anna.wildlife_sighting_tracker.models.Sighting;
import com.anna.wildlife_sighting_tracker.models.ThrivingAnimal;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

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
    Sql2oLocationDao locationDao = new Sql2oLocationDao(sql2o);

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

    // CREATE ANIMALS
    get("animals/endangered-animals/new", (request, response) -> {
      model.put("species", speciesDao.getAll());
      model.put("healthy", EndangeredAnimal.HEALTHY);
      model.put("ill", EndangeredAnimal.ILL);
      model.put("okay", EndangeredAnimal.OKAY);
      model.put("newborn", EndangeredAnimal.NEWBORN);
      model.put("young", EndangeredAnimal.YOUNG);
      model.put("adult", EndangeredAnimal.ADULT);
      model.put("edit", false);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "endangered-animal-form.hbs")
      );
    });

    post("/animals/endangered-animals", (request, response) -> {
      EndangeredAnimal endangeredAnimal = new EndangeredAnimal(
           request.queryParams("image"),
           request.queryParams("name"),
           parseInt(request.queryParams("species")),
           request.queryParams("health"),
           request.queryParams("age")
      );
      endangeredAnimalDao.add(endangeredAnimal);
      response.redirect("/animals");
      return null;
    });

    get("animals/thriving-animals/new", (request, response) -> {
      model.put("species", speciesDao.getAll());
      model.put("healthy", EndangeredAnimal.HEALTHY);
      model.put("ill", EndangeredAnimal.ILL);
      model.put("okay", EndangeredAnimal.OKAY);
      model.put("newborn", EndangeredAnimal.NEWBORN);
      model.put("young", EndangeredAnimal.YOUNG);
      model.put("adult", EndangeredAnimal.ADULT);
      model.put("edit", false);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "thriving-animal-form.hbs")
      );
    });

    post("/animals/thriving-animals", (request, response) -> {
     ThrivingAnimal thrivingAnimal = new ThrivingAnimal(
              request.queryParams("image"),
              request.queryParams("name"),
              parseInt(request.queryParams("species"))
      );
      thrivingAnimalDao.add(thrivingAnimal);
      response.redirect("/animals");
      return null;
    });

    // READ ANIMALS
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

    // UPDATE ANIMALS
    get("/animals/endangered-animals/:id/update", (request, response) -> {
      int id = parseInt(request.params("id"));
      model.put("animal", endangeredAnimalDao.get(id));
      model.put("species", speciesDao.getAll());
      model.put("healthy", EndangeredAnimal.HEALTHY);
      model.put("ill", EndangeredAnimal.ILL);
      model.put("okay", EndangeredAnimal.OKAY);
      model.put("newborn", EndangeredAnimal.NEWBORN);
      model.put("young", EndangeredAnimal.YOUNG);
      model.put("adult", EndangeredAnimal.ADULT);
      model.put("edit", true);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "endangered-animal-form.hbs")
      );
    });

    post("/animals/endangered-animals/:id/update", (request, response) -> {
      EndangeredAnimal endangeredAnimal = endangeredAnimalDao.get(parseInt(request.params("id")));
      endangeredAnimal.setImage(request.queryParams("image"));
      endangeredAnimal.setName(request.queryParams("name"));
      endangeredAnimal.setSpeciesId(parseInt(request.queryParams("species")));
      endangeredAnimal.setHealth(request.queryParams("health"));
      endangeredAnimal.setAge(request.queryParams("age"));
      endangeredAnimalDao.update(endangeredAnimal);
      response.redirect("/animals/" + request.params("id"));
      return null;
    });

    get("/animals/thriving-animals/:id/update", (request, response) -> {
      int id = parseInt(request.params("id"));
      model.put("animal", thrivingAnimalDao.get(id));
      model.put("species", speciesDao.getAll());
      model.put("healthy", EndangeredAnimal.HEALTHY);
      model.put("ill", EndangeredAnimal.ILL);
      model.put("okay", EndangeredAnimal.OKAY);
      model.put("newborn", EndangeredAnimal.NEWBORN);
      model.put("young", EndangeredAnimal.YOUNG);
      model.put("adult", EndangeredAnimal.ADULT);
      model.put("edit", true);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "thriving-animal-form.hbs")
      );
    });

    post("/animals/thriving-animals/:id/update", (request, response) -> {
      ThrivingAnimal thrivingAnimal = thrivingAnimalDao.get(parseInt(request.params("id")));
      thrivingAnimal.setImage(request.queryParams("image"));
      thrivingAnimal.setName(request.queryParams("name"));
      thrivingAnimal.setSpeciesId(parseInt(request.queryParams("species")));
      thrivingAnimalDao.update(thrivingAnimal);
      response.redirect("/animals/" + request.params("id"));
      return null;
    });

    // DELETE ANIMALS
    get("/animals/endangered-animals/:id/delete", (request, response) -> {
      EndangeredAnimal endangeredAnimal = endangeredAnimalDao.get(parseInt(request.params("id")));
      endangeredAnimalDao.delete(endangeredAnimal.getId());
      response.redirect("/animals");
      return null;
    });

    get("/animals/thriving-animals/:id/delete", (request, response) -> {
      ThrivingAnimal thrivingAnimal = thrivingAnimalDao.get(parseInt(request.params("id")));
      thrivingAnimalDao.delete(thrivingAnimal.getId());
      response.redirect("/animals");
      return null;
    });

    // CREATE SIGHTING
    get("/sightings/new", (request, response) -> {
      model.put("locations", locationDao.getAll());
      model.put("rangers", rangerDao.getAll());
      model.put("edit", false);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "sighting-form.hbs")
      );
    });

    post("/sightings", (request, response) -> {
      Sighting sighting = new Sighting(
              parseInt(request.queryParams("location")),
              parseInt(request.queryParams("ranger"))
      );
      sightingDao.add(sighting);
      response.redirect("/sightings");
      return null;
    });

    // READ SIGHTINGS
    get("/sightings", (request, response) -> {
      List<Sighting> sightings = sightingDao.getAll();

      for(Sighting sighting: sightings){
        DateTimeZone zone = DateTimeZone.forID("Africa/Nairobi");
        LocalDateTime localDateTime = new LocalDateTime(sighting.getReportedAt(), zone);
        sighting.setFormattedReportedDate(localDateTime.toString("yyyy-MMMM-dd HH:mm:ss"));
      }

      model.put("sightings", sightings);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "sightings.hbs")
      );
    });

    get("/sightings/:id", (request, response) -> {
      Sighting sighting = sightingDao.get(parseInt(request.params("id")));
      model.put("sighting", sighting);
      model.put("ranger", rangerDao.get(sighting.getRangerId()));
      model.put("location", locationDao.get(sighting.getLocationId()));
      model.put("animals", sightingDao.getAnimals(sighting.getId()));
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "sighting.hbs")
      );
    });

    get("/sightings/:id/update", (request, response) -> {
      model.put("sighting", sightingDao.get(parseInt(request.params("id"))));
      model.put("locations", locationDao.getAll());
      model.put("rangers", rangerDao.getAll());
      model.put("edit", true);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "sighting-form.hbs")
      );
    });

    post("/sightings/:id/update", (request, response) -> {
      Sighting sighting = sightingDao.get(parseInt(request.params("id")));
      sighting.setLocationId(parseInt(request.queryParams("location")));
      sighting.setRangerId(parseInt(request.queryParams("ranger")));
      sightingDao.update(sighting);
      response.redirect("/sightings/" + request.params("id"));
      return null;
    });

    // DELETE SIGHTING
    get("/sightings/:id/delete", (request, response) -> {
      Sighting sighting = sightingDao.get(parseInt(request.params("id")));
      sightingDao.delete(sighting.getId());
      response.redirect("/sightings");
      return null;
    });


    // ADD SIGHTED ANIMAL
    get("/sightings/:id/animals/new", (request, response) -> {
      Sighting sighting = sightingDao.get(parseInt(request.params("id")));
      List<Animal> animals = new ArrayList<>();
      animals.addAll(endangeredAnimalDao.getAll());
      animals.addAll(thrivingAnimalDao.getAll());

      // Edit each animal's display name to include animal species
      for(Animal animal: animals){
        String animalName = animal.getName();
        String speciesName = speciesDao.get(animal.getSpeciesId()).getName();
        animal.setName(animalName + " | Species: " + speciesName);
      }

      model.put("animals", animals);
      model.put("sighting", sighting);
      return new HandlebarsTemplateEngine().render(
              new ModelAndView(model, "sighted-animal-form.hbs")
      );
    });

    post("/sightings/:id/animals", (request, response) -> {
      Sighting sighting = sightingDao.get(parseInt(request.params("id")));
      int animalId = parseInt(request.queryParams("animal"));
      sightingDao.addAnimalToSighting(sighting.getId(), animalId);
      response.redirect("/sightings/" + request.params("id"));
      return null;
    });

    // REMOVE SIGHTED ANIMAL
    get("/sightings/:sighting_id/animals/:animal_id", (request, response) -> {
      Sighting sighting = sightingDao.get(parseInt(request.params("sighting_id")));
      sightingDao.removeAnimalFromSighting(sighting.getId(), parseInt(request.params("animal_id")));
      response.redirect("/sightings/" + request.params("sighting_id"));
      return null;
    });
  }
}