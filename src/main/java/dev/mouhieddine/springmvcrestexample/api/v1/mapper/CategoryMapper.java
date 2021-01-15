package dev.mouhieddine.springmvcrestexample.api.v1.mapper;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CategoryDTO;
import dev.mouhieddine.springmvcrestexample.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/
@Mapper
public interface CategoryMapper {
  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  CategoryDTO categoryToCategoryDTO(Category category);
}
