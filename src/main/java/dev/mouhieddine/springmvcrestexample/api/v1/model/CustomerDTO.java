package dev.mouhieddine.springmvcrestexample.api.v1.model;

import lombok.Data;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/
@Data
public class CustomerDTO {
  private Long id;
  private String firstname;
  private String lastname;
}
