package dev.mouhieddine.springmvcrestexample.api.v1.mapper;

import dev.mouhieddine.springmvcrestexample.api.v1.model.CustomerDTO;
import dev.mouhieddine.springmvcrestexample.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/
@Mapper
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO customerToCustomerDTO(Customer customer);

  Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
