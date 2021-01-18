package dev.mouhieddine.springmvcrestexample.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/
public abstract class AbstractRestControllerTest {
  public static String asJsonString(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
