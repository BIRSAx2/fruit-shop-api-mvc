package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorListDTO;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import dev.mouhieddine.springmvcrestexample.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/
@Api(tags = {"Vendors"})
@SwaggerDefinition(tags = {
        @Tag(name = "Vendors", description = "Vendors")
})
@RestController
@RequestMapping(VendorController.BASE_URL)
@Slf4j
public class VendorController {
  public static final String BASE_URL = "/api/v1/vendors";

  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @ApiOperation("Lists all the vendors.")
  @GetMapping({""})
  @ResponseStatus(HttpStatus.OK)
  public VendorListDTO getAllVendors() {
    return new VendorListDTO(vendorService.getAllVendors());
  }

  @ApiOperation("Get a vendor by id.")
  @GetMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO getVendorById(@PathVariable Long id) {
    return vendorService.getVendorById(id);
  }

  @ApiOperation("Update a vendor.")
  @PutMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @ApiOperation("Replace a vendor by new data.")
  @PatchMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.patchVendor(id, vendorDTO);
  }

  @ApiOperation("Delete a vendor.")
  @DeleteMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteVendor(@PathVariable Long id) {
    vendorService.deleteVendorById(id);
  }

  @ApiOperation("Create a vendor.")
  @PostMapping({""})
  @ResponseStatus(HttpStatus.CREATED)
  public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
    return vendorService.createNewVendor(vendorDTO);
  }
}
