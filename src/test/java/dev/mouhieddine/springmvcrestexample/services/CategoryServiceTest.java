package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CategoryMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;
import dev.mouhieddine.springmvcrestexample.domain.Category;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  public static final long ID = 1L;
  public static final String FRUITS = "Fruits";
  @Mock
  CategoryRepository categoryRepository;
  CategoryService service;


  @BeforeEach
  void setUp() {
    service = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
  }

  @Test
  void getAllCategories() {
    // given
    List<Category> categories = Arrays.asList(new Category(), new Category());
    when(categoryRepository.findAll()).thenReturn(categories);

    // when
    List<CategoryDTO> categoryDTOS = service.getAllCategories();
    // then
    assertNotNull(categoryDTOS);
    assertEquals(categories.size(), categoryDTOS.size());
  }

  @Test
  void getCategoryByName() {
    // given
    Category category = new Category();
    category.setId(ID);
    category.setName(FRUITS);

    when(categoryRepository.findByName(anyString())).thenReturn(category);
    // when
    CategoryDTO categoryDTO = service.getCategoryByName(FRUITS);
    // then
    assertNotNull(categoryDTO);
    assertEquals(ID, categoryDTO.getId());
    assertEquals(FRUITS, categoryDTO.getName());
  }
}