package org.env.windCatchers.controllers;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.env.windCatchers.dtos.accommodationUnits.AccommodationUnitResponseDTO;
import org.env.windCatchers.dtos.accommodationUnits.CreateAccommodationUnitDTO;
import org.env.windCatchers.dtos.accommodationUnits.UpdateAccommodationUnitDTO;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.services.AccommodationUnits.AccommodationUnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.github.javafaker.Faker;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AccommodationUnitsController.class)
public class AccommodationUnitsControllerTest {

    
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    AccommodationUnitService accommodationService;


    private CreateAccommodationUnitDTO createDTO;
    private AccommodationUnitResponseDTO responseDTO;
    private UpdateAccommodationUnitDTO updateDTO;


    @BeforeEach
    void setUp() {
        Faker faker = new Faker();
        createDTO = new CreateAccommodationUnitDTO();
        createDTO.setName(faker.funnyName().name());
        createDTO.setRoomType(faker.funnyName().name());
        createDTO.setMaxGuests(faker.number().randomDigit());
        createDTO.setAmenities(faker.funnyName().name());
        createDTO.setPricePerNight(new BigDecimal(99.99));

        updateDTO = new UpdateAccommodationUnitDTO();
        updateDTO.setName(faker.funnyName().name());
        updateDTO.setRoomType(faker.funnyName().name());
        updateDTO.setMaxGuests(faker.number().randomDigit());
        updateDTO.setAmenities(faker.funnyName().name());
        updateDTO.setPricePerNight(new BigDecimal(99.99));

        responseDTO = new AccommodationUnitResponseDTO();
        responseDTO.setName(createDTO.getName());
        responseDTO.setRoomType(createDTO.getRoomType());
        responseDTO.setMaxGuests(createDTO.getMaxGuests());
        responseDTO.setAmenities(createDTO.getAmenities());
        responseDTO.setPricePerNight(createDTO.getPricePerNight());
    }



    @Test // should get all the AccommodationUnits
    void shouldReturnAllAccommodationUnits() throws Exception {
        when(accommodationService.getAll()).thenReturn(List.of(responseDTO));

        mvc.perform(get("/api/accommodations"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data[0].id", is(responseDTO.getId())));
    }

    @Test // should get AccommodationUnit by id 
    void shouldFindAccommodationUnitById() throws Exception {
        when(accommodationService.getById(1L)).thenReturn(responseDTO);

        mvc.perform(get("/api/accommodations/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data.id", is(responseDTO.getId())));
    }

    @Test // should return not found if AccommodationUnit with given id not found
    void shouldReturnNotFoundAccommodationUnitById() throws Exception {
        Long notFoundId = 999L;
        when(accommodationService.getById(notFoundId))
        .thenThrow(new ResourceNotFoundException("Accommodation Unit not found with ID: " + notFoundId));

        mvc.perform(get("/api/accommodations/{id}", notFoundId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success", is(false)));
    }

    @Test // should create new AccommodationUnit
    void shouldCreateNewAccommodationUnit() throws Exception {
     when(accommodationService.create(any(CreateAccommodationUnitDTO.class))).thenReturn(responseDTO);
     
     mvc.perform(post("/api/accommodations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(createDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.success",is(true)));
    }

    @Test // should update AccommodationUnit by given Id 
    void shouldUpdateAccommodationUnit() throws Exception {
        when(accommodationService.update(eq(1L), any(UpdateAccommodationUnitDTO.class))).thenReturn(responseDTO);

        mvc.perform(put("/api/accommodations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data.id", is(responseDTO.getId())))
            .andExpect(jsonPath("$.data.name", is(responseDTO.getName())))
            .andExpect(jsonPath("$.data.maxGuests", is(responseDTO.getMaxGuests())));
    }

    @Test // should return not found if target AccommodationUnit is not found
    void updateAccommodationShouldReturnNotFound() throws Exception {
        Long notFoundId = 999L;
        when(accommodationService.update(eq(notFoundId), any(UpdateAccommodationUnitDTO.class)))
        .thenThrow(new ResourceNotFoundException("Accommodation Unit not found with ID: " + notFoundId));

        mvc.perform(put("/api/accommodations/{id}", notFoundId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.error.message", is("Accommodation Unit not found with ID: " + notFoundId)));
    }

    @Test // should delete  AccommodationUnit with given id
    public void shouldDeleteAccommodationUnit() throws Exception {
        doNothing().when(accommodationService).delete(1L);

        mvc.perform(delete("/api/accommodations/{id}", 1L))
            .andExpect(status().isOk());
    }
}
