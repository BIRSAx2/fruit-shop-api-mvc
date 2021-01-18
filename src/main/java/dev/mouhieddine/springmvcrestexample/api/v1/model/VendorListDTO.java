package dev.mouhieddine.springmvcrestexample.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/18/2021, Monday
 **/
@Data
@AllArgsConstructor
public class VendorListDTO {
  List<VendorDTO> vendors;
}
