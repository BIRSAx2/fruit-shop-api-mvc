package dev.mouhieddine.springmvcrestexample.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/

@Data
public class VendorDTO {
  private Long id;
  private String name;
  @JsonProperty("vendor_url")
  private String vendorUrl;
}
