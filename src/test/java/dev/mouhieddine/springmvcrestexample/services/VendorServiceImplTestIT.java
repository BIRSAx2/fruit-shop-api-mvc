package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.VendorMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.bootstrap.Bootstrap;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import dev.mouhieddine.springmvcrestexample.repositories.CategoryRepository;
import dev.mouhieddine.springmvcrestexample.repositories.CustomerRepository;
import dev.mouhieddine.springmvcrestexample.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @author : Mouhieddine.dev
 * @since : 1/18/2021, Monday
 **/

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
class VendorServiceImplTestIT {

  @Autowired
  VendorRepository vendorRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  CategoryRepository categoryRepository;

  VendorService vendorService;

  @BeforeEach
  void setUp() throws Exception {
    log.debug("Loading vendor data for integration test");

    // setup data for testing
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
    bootstrap.run();

    vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
  }

  @Test
  void patchVendor() {
    // given
    final String newName = "Nuts for Nuts Company";
    Long id = getVendorIdValue();

    Vendor originalVendor = vendorRepository.getOne(id);
    assertNotNull(originalVendor);

    // saving original name
    String originalName = originalVendor.getName();

    // creating a DTO with an updated name
    VendorDTO vendorDTO = new VendorDTO();
    vendorDTO.setName(newName);

    // when
    vendorService.patchVendor(id, vendorDTO);
    Vendor patchedVendor = vendorRepository.getOne(id);

    // then

    assertNotNull(patchedVendor);
    assertEquals(newName, patchedVendor.getName());

  }

  private Long getVendorIdValue() {
    return vendorRepository.findAll().get(0).getId();
  }
}