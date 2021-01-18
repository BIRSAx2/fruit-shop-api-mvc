package dev.mouhieddine.springmvcrestexample.services;

import dev.mouhieddine.springmvcrestexample.api.v1.mapper.VendorMapper;
import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.controllers.v1.VendorController;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import dev.mouhieddine.springmvcrestexample.exceptions.ResourceNotFoundException;
import dev.mouhieddine.springmvcrestexample.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/

@Service
public class VendorServiceImpl implements VendorService {

  private final VendorRepository vendorRepository;
  private final VendorMapper mapper;

  public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper mapper) {
    this.vendorRepository = vendorRepository;
    this.mapper = mapper;
  }

  @Override
  public List<VendorDTO> getAllVendors() {
    return vendorRepository.findAll().stream().map(vendor -> {
      VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
      vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
      return vendorDTO;
    }).collect(Collectors.toList());
  }

  @Override
  public VendorDTO getVendorById(Long id) {
    return vendorRepository.findById(id).map(vendor -> {
      VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
      vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
      return vendorDTO;
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public VendorDTO createNewVendor(VendorDTO vendorDTO) {
    return saveAndReturnDTO(mapper.vendorDTOToVendor(vendorDTO));
  }

  @Override
  public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
    Vendor vendor = mapper.vendorDTOToVendor(vendorDTO);
    vendor.setId(id);
    return saveAndReturnDTO(vendor);
  }

  @Override
  public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
    return vendorRepository.findById(id).map(vendor -> {
      if (vendorDTO.getName() != null) vendor.setName(vendorDTO.getName());
      VendorDTO returnDTO = mapper.vendorToVendorDTO(vendor);
      returnDTO.setVendorUrl(getVendorUrl(id));
      return returnDTO;
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public void deleteVendorById(Long id) {
    vendorRepository.deleteById(id);
  }

  private String getVendorUrl(Long id) {
    return VendorController.BASE_URL + "/" + id;
  }

  private VendorDTO saveAndReturnDTO(Vendor vendor) {
    Vendor savedVendor = vendorRepository.save(vendor);
    VendorDTO returnDTO = mapper.vendorToVendorDTO(savedVendor);
    returnDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));
    return returnDTO;
  }
}
