package com.anna.wildlife_sighting_tracker;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;
public class App {
  public static void main(String[] args) {
    staticFileLocation("public/");
    get("/", (request, response) -> new HandlebarsTemplateEngine().render(
            new ModelAndView(new HashMap<>(), "index.hbs")
    ));
  }
}