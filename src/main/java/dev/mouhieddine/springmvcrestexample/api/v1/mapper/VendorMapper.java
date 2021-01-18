package dev.mouhieddine.springmvcrestexample.api.v1.mapper;

import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Mouhieddine.dev
 * @since : 1/17/2021, Sunday
 **/
@Mapper
public interface VendorMapper {

  VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

  VendorDTO vendorToVendorDTO(Vendor vendor);

  Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
