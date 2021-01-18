package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CustomerMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.bootstrap.Bootstrap;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import dev.mouhieddine.springmvcrestexample.repositories.CustomerRepository;
import dev.mouhieddine.springmvcrestexample.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerServiceImplIT {

  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  CategoryRepository categoryRepository;
  @Autowired
  VendorRepository vendorRepository;

  CustomerService customerService;

  @BeforeEach
  void setUp() throws Exception {
    log.debug("Loading customer data for integration test");

    // setup data for testing
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
    bootstrap.run();

    customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  void patchCustomerUpdateFirstname() {
    // given
    final String newFirstname = "UpdatedFirstname";
    Long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    // saving original firstname and lastname
    String originalFirstname = originalCustomer.getFirstname();
    String originalLastname = originalCustomer.getLastname();

    // creating a DTO with an updated name
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(newFirstname);

    // when
    customerService.patchCustomer(id, customerDTO);
    Customer updatedCustomer = customerRepository.getOne(id);

    // then
    assertNotNull(updatedCustomer);
    assertEquals(newFirstname, updatedCustomer.getFirstname());
    assertNotEquals(originalFirstname, updatedCustomer.getFirstname());
    assertEquals(originalLastname, updatedCustomer.getLastname());

  }

  @Test
  void patchCustomerUpdateLastname() {
    // given
    final String newLastname = "UpdatedLastname";
    Long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    // saving original firstname and lastname
    String originalFirstname = originalCustomer.getFirstname();
    String originalLastname = originalCustomer.getLastname();

    // creating a DTO with an updated name
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setLastname(newLastname);

    // when
    customerService.patchCustomer(id, customerDTO);
    Customer patchedCustomer = customerRepository.getOne(id);

    // then
    assertNotNull(patchedCustomer);
    assertEquals(newLastname, patchedCustomer.getLastname());
    assertEquals(originalFirstname, patchedCustomer.getFirstname());
    assertNotEquals(originalLastname, patchedCustomer.getLastname());
  }

  private Long getCustomerIdValue() {
    return customerRepository.findAll().get(0).getId();
  }
}