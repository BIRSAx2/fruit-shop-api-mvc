package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.CustomerMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.controllers.v1.CustomerController;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
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
    return customerRepository.findAll().stream()
            .map(customer -> {
              CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
              customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
              return customerDTO;
            })
            .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {
    return customerRepository.findById(id)
            .map(customer -> {
              CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
              customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
              return customerDTO;
            })
            .orElseThrow(RuntimeException::new);
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
    return saveAndReturnDTO(mapper.customerDTOToCustomer(customerDTO));
  }

  @Override
  public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
    Customer customer = mapper.customerDTOToCustomer(customerDTO);
    customer.setId(id);
    return saveAndReturnDTO(customer);
  }

  private CustomerDTO saveAndReturnDTO(Customer customer) {
    Customer savedCustomer = customerRepository.save(customer);
    CustomerDTO returnDTO = mapper.customerToCustomerDTO(savedCustomer);
    returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
    return returnDTO;
  }

  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
    return customerRepository.findById(id).map(customer -> {
      if (customerDTO.getFirstname() != null) customer.setFirstname(customerDTO.getFirstname());
      if (customerDTO.getLastname() != null) customer.setLastname(customerDTO.getLastname());
      CustomerDTO returnDTO = mapper.customerToCustomerDTO(customerRepository.save(customer));
      returnDTO.setCustomerUrl(getCustomerUrl(id));
      return returnDTO;
    }).orElseThrow(RuntimeException::new);
  }

  @Override
  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }

  private String getCustomerUrl(Long id) {
    return CustomerController.BASE_URL + "/" + id;
  }
}
