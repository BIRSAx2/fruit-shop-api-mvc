package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CustomerMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.controllers.v1.CustomerController;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
import dev.mouhieddine.springmvcrestexample.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  public static final long ID = 1L;
  public static final String FIRSTNAME = "Joe";
  public static final String LASTNAME = "Newman";
  @Mock
  CustomerRepository customerRepository;

  CustomerService customerService;

  @BeforeEach
  void setUp() {
    customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  void getAllCustomers() {
    // given
    List<Customer> customers = Arrays.asList(new Customer(), new Customer());
    when(customerRepository.findAll()).thenReturn(customers);

    // when
    List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

    // then
    assertNotNull(customerDTOS);
    assertEquals(2, customerDTOS.size());

  }

  @Test
  void getCustomerById() {
    // given
    Customer joe = new Customer();
    joe.setId(ID);
    joe.setFirstname(FIRSTNAME);
    joe.setLastname(LASTNAME);
    when(customerRepository.findById(anyLong())).thenReturn(Optional.of(joe));

    // when
    CustomerDTO customerDTO = customerService.getCustomerById(ID);

    // then
    assertNotNull(customerDTO);
    assertEquals(ID, customerDTO.getId());
    assertEquals(FIRSTNAME, customerDTO.getFirstname());
    assertEquals(LASTNAME, customerDTO.getLastname());
  }

  @Test
  void createNewCustomer() {
    // given
    CustomerDTO joe = new CustomerDTO();
    joe.setFirstname(FIRSTNAME);
    joe.setLastname("Newman");
    joe.setCustomerUrl(CustomerController.BASE_URL+"/1");

    Customer savedJoe = new Customer();
    savedJoe.setId(1L);
    savedJoe.setFirstname(FIRSTNAME);
    savedJoe.setLastname("Newman");

    when(customerRepository.save(any(Customer.class))).thenReturn(savedJoe);

    // when
    CustomerDTO savedDTO = customerService.createNewCustomer(joe);

    // then
    assertNotNull(savedDTO);
    assertEquals(FIRSTNAME, savedDTO.getFirstname());
    assertEquals(CustomerController.BASE_URL+"/1", savedDTO.getCustomerUrl());

  }

  @Test
  void saveCustomerByDTO() {

    // given
    CustomerDTO joe = new CustomerDTO();
    joe.setFirstname(FIRSTNAME);
    joe.setLastname("Newman");
    joe.setCustomerUrl(CustomerController.BASE_URL + "/1");

    Customer savedJoe = new Customer();
    savedJoe.setId(ID);
    savedJoe.setFirstname(FIRSTNAME);
    savedJoe.setLastname("Newman");

    when(customerRepository.save(any(Customer.class))).thenReturn(savedJoe);

    // when
    CustomerDTO savedDTO = customerService.saveCustomerByDTO(ID, joe);

    // then
    assertNotNull(savedDTO);
    assertEquals(FIRSTNAME, savedDTO.getFirstname());
    assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerUrl());


  }

  @Test
  void deleteCustomerById() {
    final Long id = 1L;
    customerService.deleteCustomerById(id);

    verify(customerRepository, times(1)).deleteById(id);
  }
}