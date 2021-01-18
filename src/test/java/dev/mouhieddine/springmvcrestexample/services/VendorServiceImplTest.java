package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.VendorMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.controllers.v1.VendorController;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import dev.mouhieddine.springmvcrestexample.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

  public static final String NAME = "Shelby Company Limited";
  private static final Long ID = 1L;
  @Mock
  VendorRepository vendorRepository;

  VendorService vendorService;

  @BeforeEach
  void setUp() {
    vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
  }

  @Test
  void getAllVendors() {
    // given
    List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
    when(vendorRepository.findAll()).thenReturn(vendors);

    // when
    List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

    // then
    assertNotNull(vendorDTOS);
    assertEquals(2, vendorDTOS.size());

  }

  @Test
  void getVendorById() {
    // given
    Vendor vendor = new Vendor();
    vendor.setId(ID);
    vendor.setName(NAME);
    when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

    // when
    VendorDTO vendorDTO = vendorService.getVendorById(ID);

    // then
    assertNotNull(vendorDTO);
    assertEquals(ID, vendorDTO.getId());
    assertEquals(NAME, vendorDTO.getName());
  }

  @Test
  void createNewVendor() {
    // given
    VendorDTO vendor = new VendorDTO();
    vendor.setName(NAME);
    vendor.setVendorUrl(VendorController.BASE_URL + "/1");

    Vendor savedVendor = new Vendor();
    savedVendor.setId(ID);
    savedVendor.setName(NAME);

    when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
    // when
    VendorDTO savedDTO = vendorService.createNewVendor(vendor);
    //then
    assertNotNull(savedDTO);
    assertEquals(NAME, savedDTO.getName());
    assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());

  }

  @Test
  void saveVendorByDTO() {
    // given
    VendorDTO vendor = new VendorDTO();
    vendor.setName(NAME);
    vendor.setVendorUrl(VendorController.BASE_URL + "/1");

    Vendor savedVendor = new Vendor();
    savedVendor.setId(ID);
    savedVendor.setName(NAME);

    when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
    // when
    VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendor);
    //then
    assertNotNull(savedDTO);
    assertEquals(NAME, savedDTO.getName());
    assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
  }

  @Test
  void deleteVendorByDTO() {
    vendorService.deleteVendorById(ID);
    verify(vendorRepository,times(1)).deleteById(ID);
  }
}