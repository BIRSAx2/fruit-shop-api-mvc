package dev.mouhieddine.springmvcrestexample.bootstrap;

import dev.mouhieddine.springmvcrestexample.domain.Category;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import dev.mouhieddine.springmvcrestexample.repositories.CustomerRepository;
import dev.mouhieddine.springmvcrestexample.repositories.VendorRepository;
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
  private final VendorRepository vendorRepository;

  public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadCategories();
    loadCustomers();
    loadVendors();

    log.debug("Loading boostrap data");
  }

  private void loadCustomers() {
    Customer joe = new Customer();
    joe.setFirstname("Joe");
    joe.setLastname("Newman");

    Customer faiz = new Customer();
    faiz.setFirstname("Faiz");
    faiz.setLastname("Wasim");

    Customer freddy = new Customer();
    freddy.setFirstname("Freddy");
    freddy.setLastname("Meyers");

    Customer ramazan = new Customer();
    ramazan.setFirstname("Ramazan");
    ramazan.setLastname("Demir");

    customerRepository.saveAll(Arrays.asList(joe, faiz, freddy, ramazan));
  }

  private void loadCategories() {
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
  }

  private void loadVendors() {
    Vendor shelby = new Vendor();
    shelby.setName("Shelby Company Limited");

    Vendor western = new Vendor();
    western.setName("Western Tasty Fruits Ltd.");

    Vendor exotic = new Vendor();
    exotic.setName("Exotic Fruits Company");

    vendorRepository.saveAll(Arrays.asList(shelby, western, exotic));
  }
}
