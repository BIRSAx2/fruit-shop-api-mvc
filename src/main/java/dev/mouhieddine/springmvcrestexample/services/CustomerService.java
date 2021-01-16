package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/
public interface CustomerService {

  List<CustomerDTO> getAllCustomers();

  CustomerDTO getCustomerById(Long id);

  CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
