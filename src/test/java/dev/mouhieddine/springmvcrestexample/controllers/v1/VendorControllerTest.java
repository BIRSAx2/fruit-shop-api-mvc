package dev.mouhieddine.springmvcrestexample.controllers.v1;

import dev.mouhieddine.springmvcrestexample.api.v1.model.VendorDTO;
import dev.mouhieddine.springmvcrestexample.exceptions.ResourceNotFoundException;
import dev.mouhieddine.springmvcrestexample.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static dev.mouhieddine.springmvcrestexample.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author : Mouhieddine.dev
 * @since : 1/18/2021, Monday
 **/
@ExtendWith(MockitoExtension.class)
class VendorControllerTest {
  public static final String NAME = "Shelby Company Limited";
  @Mock
  VendorService vendorService;
  @InjectMocks
  VendorController controller;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new RestResponseEntityExceptionHandler())
            .build();
  }

  @Test
  void getAllVendors() throws Exception {
    // given
    VendorDTO shelby = new VendorDTO();
    shelby.setName(NAME);

    VendorDTO western = new VendorDTO();
    western.setName("Western Tasty Fruits Ltd.");

    VendorDTO exotic = new VendorDTO();
    exotic.setName("Exotic Fruits Company");

    when(vendorService.getAllVendors()).thenReturn(Arrays.asList(shelby, western, exotic));

    // when/then

    mockMvc.perform(get(VendorController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.vendors", hasSize(3)));
  }

  @Test
  void getVendorById() throws Exception {
    // given
    VendorDTO shelby = new VendorDTO();
    shelby.setName(NAME);
    shelby.setVendorUrl(VendorController.BASE_URL + "/1");

    when(vendorService.getVendorById(anyLong())).thenReturn(shelby);

    // when/then
    mockMvc.perform(get(VendorController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", equalTo(NAME)));
  }

  @Test
  void createNewVendor() throws Exception {
    // given

    VendorDTO shelby = new VendorDTO();
    shelby.setName(NAME);

    VendorDTO returnShelby = new VendorDTO();
    returnShelby.setName(NAME);
    returnShelby.setVendorUrl(VendorController.BASE_URL + "/1");

    when(vendorService.createNewVendor(shelby)).thenReturn(returnShelby);

//     when/then
    mockMvc.perform(post(VendorController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(shelby)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", equalTo(NAME)))
            .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
  }

  @Test
  void updateVendorByDTO() throws Exception {
    // given
    VendorDTO shelby = new VendorDTO();
    shelby.setName("Old name");

    VendorDTO returnShelby = new VendorDTO();
    returnShelby.setName(NAME);
    returnShelby.setVendorUrl(VendorController.BASE_URL + "/1");

    when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnShelby);

    // when/then

    mockMvc.perform(put(VendorController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(shelby)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", equalTo(NAME)))
            .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
  }

  @Test
  void patchVendor() throws Exception {
    // given
    final String NEW_NAME = "Western Tasty Fruits Ltd.";
    VendorDTO shelby = new VendorDTO();
    shelby.setName(NAME);

    VendorDTO patchedShelby = new VendorDTO();
    patchedShelby.setName(NEW_NAME);
    patchedShelby.setVendorUrl(VendorController.BASE_URL + "/1");

    when(vendorService.patchVendor(anyLong(), any(VendorDTO.class)))
            .thenReturn(patchedShelby);

    // when/then
    mockMvc.perform(patch(VendorController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(patchedShelby)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", equalTo(NEW_NAME)))
            .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
  }

  @Test
  void deleteVendor() throws Exception {

    // when
    mockMvc.perform(delete(VendorController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    // then
    verify(vendorService, times(1)).deleteVendorById(anyLong());
  }

  @Test
  void getVendorByIdNotFound() throws Exception {

    when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

    // when/then
    mockMvc.perform(get(VendorController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
}