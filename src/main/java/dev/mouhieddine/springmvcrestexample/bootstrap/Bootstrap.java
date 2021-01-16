package dev.mouhieddine.springmvcrestexample.bootstrap;

import dev.mouhieddine.springmvcrestexample.domain.Category;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import dev.mouhieddine.springmvcrestexample.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : Mouhieddine.dev
 * @since : 1/15/2021, Friday
 **/

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;

  public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
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

    categoryRepository.saveAll(Arrays.asList(fruits, dried, fresh, exotic, nuts));


    Customer joe = new Customer();
    joe.setFirstname("Joe");
    joe.setLastname("Newman");

    Customer faiz = new Customer();
    faiz.setFirstname("Faiz");
    faiz.setFirstname("Wasim");

    Customer freddy = new Customer();
    freddy.setFirstname("Freddy");
    freddy.setFirstname("Meyers");

    Customer ramazan = new Customer();
    ramazan.setFirstname("Ramazan");
    ramazan.setFirstname("Demir");

    customerRepository.saveAll(Arrays.asList(joe, faiz, freddy, ramazan));

    log.debug("Loading boostrap data. Data Loaded = " + categoryRepository.count());
  }
}
