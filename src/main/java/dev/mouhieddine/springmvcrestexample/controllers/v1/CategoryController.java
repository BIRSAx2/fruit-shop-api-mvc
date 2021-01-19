package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryListDTO;
import dev.mouhieddine.springmvcrestexample.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
@Api(tags = {"Categories"})
@SwaggerDefinition(tags = {
        @Tag(name = "Categories", description = "Categories")
})
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
  public static final String BASE_URL = "/api/v1/categories";

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping({"", "/"})
  @ResponseStatus(HttpStatus.OK)
  public CategoryListDTO getAllCategories() {
    return new CategoryListDTO(categoryService.getAllCategories());
  }

  @GetMapping({"/{name}", "{name}"})
  public CategoryDTO getCategoryByName(@PathVariable String name) {
    return categoryService.getCategoryByName(name);
  }
}
