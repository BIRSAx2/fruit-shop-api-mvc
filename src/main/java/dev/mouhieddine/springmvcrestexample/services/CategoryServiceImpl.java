package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CategoryMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper mapper;

  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
    this.categoryRepository = categoryRepository;
    this.mapper = mapper;
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll().stream().map(mapper::categoryToCategoryDTO).collect(Collectors.toList());
  }

  @Override
  public CategoryDTO getCategoryByName(String categoryName) {
    return mapper.categoryToCategoryDTO(categoryRepository.findByName(categoryName));
  }
}
