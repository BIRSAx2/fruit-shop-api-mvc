package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CustomerMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper mapper;

  public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
    this.customerRepository = customerRepository;
    this.mapper = mapper;
  }


  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream().map(mapper::customerToCustomerDTO).collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerByName(String customerName) {
    return mapper.customerToCustomerDTO(customerRepository.findByName(customerName));
  }
}
