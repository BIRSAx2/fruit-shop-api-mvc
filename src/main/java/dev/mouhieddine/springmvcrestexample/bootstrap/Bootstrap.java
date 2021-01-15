package dev.mouhieddine.springmvcrestexample.bootstrap;

import dev.mouhieddine.springmvcrestexample.domain.Category;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/

@Slf4j
public class Bootstrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;

  public Bootstrap(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");

    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);


    log.debug("Loading boostrap data. Data Loaded = " + categoryRepository.count());
  }
}
