package dev.mouhieddine.springmvcrestexample.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
@Data
@AllArgsConstructor
public class CategoryListDTO {
  List<CategoryDTO> categories;
}
