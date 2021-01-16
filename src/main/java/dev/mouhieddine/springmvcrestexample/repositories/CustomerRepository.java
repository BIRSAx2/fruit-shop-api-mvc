package dev.mouhieddine.springmvcrestexample.repositories;

import dev.mouhieddine.springmvcrestexample.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Mouhieddine.dev
 * @since : 1/16/2021, Saturday
 **/

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByName(String customerName);
}
