package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;

import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/
public interface VendorService {

  List<VendorDTO> getAllVendors();

  VendorDTO getVendorById(Long id);

  VendorDTO createNewVendor(VendorDTO vendorDTO);

  VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

  VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

  void deleteVendorById(Long id);
}
