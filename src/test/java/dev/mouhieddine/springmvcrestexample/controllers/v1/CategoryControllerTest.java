package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;
import dev.mouhieddine.springmvcrestexample.exceptions.ResourceNotFoundException;
import dev.mouhieddine.springmvcrestexample.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

  public static final String FRUITS = "Fruits";
  @Mock
  CategoryService categoryService;
  @InjectMocks
  CategoryController categoryController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
            .setControllerAdvice(new RestResponseEntityExceptionHandler())
            .build();
  }

  @Test
  void listCategories() throws Exception {
    // given
    CategoryDTO cat1 = new CategoryDTO();
    cat1.setId(1L);
    cat1.setName(FRUITS);
    CategoryDTO cat2 = new CategoryDTO();
    cat2.setId(2L);
    cat2.setName("Vegetables");

    when(categoryService.getAllCategories()).thenReturn(Arrays.asList(cat1, cat2));

    // then
    mockMvc.perform(get(CategoryController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.categories", hasSize(2)));
  }

  @Test
  void findCategoryByName() throws Exception {
    // given
    CategoryDTO cat1 = new CategoryDTO();
    cat1.setId(1L);
    cat1.setName(FRUITS);
    when(categoryService.getCategoryByName(anyString())).thenReturn(cat1);

    // then
    mockMvc.perform(get(CategoryController.BASE_URL + "/" + FRUITS)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", equalTo(FRUITS)));
  }

  @Test
  void findCategoryByNameNotFound() throws Exception {
    // given
    when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

    // when / then
    mockMvc.perform(get(CategoryController.BASE_URL + "/Foo")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
}