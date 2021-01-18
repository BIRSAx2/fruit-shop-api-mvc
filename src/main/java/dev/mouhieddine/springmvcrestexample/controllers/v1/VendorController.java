package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorListDTO;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import dev.mouhieddine.springmvcrestexample.services.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/
@RestController
@RequestMapping(VendorController.BASE_URL)
@Slf4j
public class VendorController {
  public static final String BASE_URL = "/api/v1/vendors";

  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping({"", "/"})
  @ResponseStatus(HttpStatus.OK)
  public VendorListDTO getAllVendors() {
    return new VendorListDTO(vendorService.getAllVendors());
  }

  @GetMapping({"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO getVendorById(@PathVariable Long id) {
    return vendorService.getVendorById(id);
  }

  @PutMapping({"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @PatchMapping({"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.patchVendor(id, vendorDTO);
  }

  @DeleteMapping({"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteVendor(@PathVariable Long id) {
    vendorService.deleteVendorById(id);
  }

  @PostMapping({"", "/"})
  @ResponseStatus(HttpStatus.CREATED)
  public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
    return vendorService.createNewVendor(vendorDTO);
  }
}
