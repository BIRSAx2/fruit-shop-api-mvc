package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerListDTO;
import dev.mouhieddine.springmvcrestexample.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return new ResponseEntity<CustomerListDTO>(
            new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping({"/{name}", "/{name}/"})
  public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {
    return new ResponseEntity<CustomerDTO>(customerService.getCustomerByName(name), HttpStatus.OK);
  }
}
