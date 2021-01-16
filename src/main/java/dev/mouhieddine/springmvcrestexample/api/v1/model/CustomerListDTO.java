package dev.mouhieddine.springmvcrestexample.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

@Data
@AllArgsConstructor
public class CustomerListDTO {
  private List<CustomerDTO> customers;
}
