package dev.mouhieddine.springmvcrestexample.api.v1.mapper;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/
class CustomerMapperTest {
  public static final long ID = 1L;
  public static final String FIRSTNAME = "Joe";
  public static final String LASTNAME = "Newman";
  CustomerMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = CustomerMapper.INSTANCE;
  }

  @Test
  void customerToCustomerDTO() {
    // given
    Customer joe = new Customer();
    joe.setId(ID);
    joe.setFirstname(FIRSTNAME);
    joe.setLastname(LASTNAME);

    // when
    CustomerDTO customerDTO = mapper.customerToCustomerDTO(joe);

    // then
    assertNotNull(customerDTO);
    assertEquals(ID, customerDTO.getId());
    assertEquals(FIRSTNAME, customerDTO.getFirstname());
    assertEquals(LASTNAME, customerDTO.getLastname());
  }
}