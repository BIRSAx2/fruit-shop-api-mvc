package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerListDTO;
import dev.mouhieddine.springmvcrestexample.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping({"", "/"})
  public ResponseEntity<CustomerListDTO> getAllCustomers() {
    return new ResponseEntity<>(
            new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping({"/{id}", "/{id}/"})
  public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable Long id) {
    return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @PostMapping({"", "/"})
  public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
    return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),
            HttpStatus.CREATED);
  }
}
