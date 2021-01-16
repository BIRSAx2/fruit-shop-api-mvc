package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CustomerMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  public static final long ID = 1L;
  public static final String FIRSTNAME = "Joe";
  public static final String LASTNAME = "Newman";
  @Mock
  CustomerRepository customerRepository;

  CustomerService service;

  @BeforeEach
  void setUp() {
    service = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  void getAllCustomers() {
    // given
    List<Customer> customers = Arrays.asList(new Customer(), new Customer());
    when(customerRepository.findAll()).thenReturn(customers);

    // when
    List<CustomerDTO> customerDTOS = service.getAllCustomers();

    // then
    assertNotNull(customerDTOS);
    assertEquals(2, customerDTOS.size());

  }

  @Test
  void getCustomerByName() {
    // given
    Customer joe = new Customer();
    joe.setId(ID);
    joe.setFirstname(FIRSTNAME);
    joe.setLastname(LASTNAME);
    when(customerRepository.findById(anyLong())).thenReturn(Optional.of(joe));

    // when
    CustomerDTO customerDTO = service.getCustomerById(ID);

    // then
    assertNotNull(customerDTO);
    assertEquals(ID, customerDTO.getId());
    assertEquals(FIRSTNAME, customerDTO.getFirstname());
    assertEquals(LASTNAME, customerDTO.getLastname());
  }
}