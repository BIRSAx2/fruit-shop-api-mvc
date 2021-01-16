package dev.mouhieddine.springmvcrestexample.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/
public abstract class AbstractRestControllerTest {
  public static String asJsonString(CustomerDTO customerDTO) {
    try {
      return new ObjectMapper().writeValueAsString(customerDTO);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
