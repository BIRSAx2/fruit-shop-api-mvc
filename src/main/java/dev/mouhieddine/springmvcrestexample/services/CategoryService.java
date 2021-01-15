package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
public interface CategoryService {

  List<CategoryDTO> getAllCategories();
  CategoryDTO getCategoryByName(String categoryName);
}
