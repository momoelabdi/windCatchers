package org.env.windCatchers.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.env.windCatchers.dtos.roomBooking.CreateRoomBookingDTO;
import org.env.windCatchers.dtos.roomBooking.RoomBookingResponseDTO;
import org.env.windCatchers.services.roomBooking.RoomBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@WebMvcTest(RoomBookingsController.class)
public class RoomBookingsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    RoomBookingService service;

    private 
    CreateRoomBookingDTO createDTO;
    RoomBookingResponseDTO responseDTO; 


    @BeforeEach
    void setup() {
        Faker faker = new Faker();
        createDTO = new CreateRoomBookingDTO();
        createDTO.setAccommodationUnitId(1L);
        createDTO.setCheckIn(LocalDateTime.now().toLocalDate());
        createDTO.setCheckOut(LocalDateTime.now().plusDays(2).toLocalDate());
        createDTO.setGuestsCount(faker.random().nextInt(1, 4));
        createDTO.setBookingStatus(faker.lorem().characters());
        createDTO.setPaymentStatus(faker.lorem().characters());



        responseDTO = new RoomBookingResponseDTO();
        responseDTO.setCheckIn(createDTO.getCheckIn());
        responseDTO.setCheckOut(createDTO.getCheckOut());
        responseDTO.setBookingStatus(createDTO.getBookingStatus());
        responseDTO.setGuestsCount(createDTO.getGuestsCount());
        responseDTO.setPaymentStatus(createDTO.getPaymentStatus());
    }

    

    @Test 
    void shouldListAllRoomBookings() throws Exception {
        when(service.getAll()).thenReturn(List.of(responseDTO));

        mvc.perform(get("/api/room_bookings"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data[0]", is(mapper.convertValue(responseDTO, Map.class))));
    }


    @Test
    void shouldCreateNewRoomBooking() throws Exception {
        when(service.create(any(CreateRoomBookingDTO.class))).thenReturn(responseDTO);
        
        mvc.perform(post("/api/room_bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.checkIn", is(responseDTO.getCheckIn().toString())))
            .andExpect(jsonPath("$.data.guestsCount", is(responseDTO.getGuestsCount())))
            .andExpect(jsonPath("$.data.bookingStatus", is(responseDTO.getBookingStatus())))
            .andExpect(jsonPath("$.data.checkOut", is(responseDTO.getCheckOut().toString())));
    }
}
