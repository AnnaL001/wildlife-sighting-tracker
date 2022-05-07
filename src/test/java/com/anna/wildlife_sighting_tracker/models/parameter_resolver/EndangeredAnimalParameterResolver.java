package com.anna.wildlife_sighting_tracker.models.parameter_resolver;

import com.anna.wildlife_sighting_tracker.models.EndangeredAnimal;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class EndangeredAnimalParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == EndangeredAnimal.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return new EndangeredAnimal(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm8oUUKb6GMiSeAnjvgDYnnFM1OAWPIMzqcm6-tyuEG6MxwGv8NMCdxkC8wLWOjJf7qs0&usqp=CAU",
            "Zippy",
            2,
            EndangeredAnimal.OKAY,
            EndangeredAnimal.YOUNG
    );
  }
}
