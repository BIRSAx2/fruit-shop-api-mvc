package dev.mouhieddine.springmvcrestexample.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
  private Long id;
  private String name;
}
