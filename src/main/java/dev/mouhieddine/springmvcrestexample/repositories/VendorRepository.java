package dev.mouhieddine.springmvcrestexample.repositories;

import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
