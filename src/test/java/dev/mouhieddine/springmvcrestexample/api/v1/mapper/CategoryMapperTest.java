package dev.mouhieddine.springmvcrestexample.api.v1.mapper;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;
import dev.mouhieddine.springmvcrestexample.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
class CategoryMapperTest {
  
  CategoryMapper mapper = CategoryMapper.INSTANCE;
  private String fruits = "Fruits";
  private long id = 1L;


  @Test
  void categoryToCategoryDTO() {
    // given
    Category category = new Category();
    category.setName(fruits);
    category.setId(id);
    // when
    CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);
    //then
    assertNotNull(categoryDTO);
    assertEquals(id, categoryDTO.getId());
    assertEquals(fruits, categoryDTO.getName());
  }
}