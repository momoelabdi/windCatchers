package org.env.windCatchers.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.env.windCatchers.enums.Enums.ScheduleStatus;
import org.env.windCatchers.enums.Enums.BookingStatus;
import org.env.windCatchers.enums.Enums.PaymentStatus;
import org.env.windCatchers.model.Booking;
import org.env.windCatchers.model.Schedule;
import org.env.windCatchers.model.User;
import org.env.windCatchers.repository.BookingRepository;
import org.env.windCatchers.service.CreateBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(BookingsController.class)
public class BookingsControllerTest {
    
    @Autowired
    MockMvc mvc;

    @Autowired 
    ObjectMapper mapper;


    @MockitoBean
    BookingRepository repository;

    @MockitoBean
    CreateBookingService service;


    private final List<Booking> bookings = new ArrayList<>();

    @BeforeEach
    void setUp () {
        User user = new User("John", "john@mail.com", "password123", "Admin", "0100000000");
        LocalDateTime start = LocalDateTime.of(2025, 7, 15, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 7, 15, 12, 0);
        Schedule schedule = new Schedule(start, end, "Wind Farm Zone A", ScheduleStatus.OPEN);
        Booking booking = new Booking(BookingStatus.CONFIRMED, PaymentStatus.PAID);
        booking.setUser(user);
        booking.setSchedule(schedule);
        bookings.add(booking);
     }


     @Test
     void shouldFindAllBookings() throws Exception {
        when(repository.findAll()).thenReturn(bookings);
        mvc.perform(get("/api/bookings"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(bookings.size())));
     }

     @Test 
     void shouldCreateNewBooking() throws Exception {
        Booking booking = bookings.get(0);
        when(service.createBooking(booking)).thenReturn(booking);
        mvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(booking)))
                .andExpect(status().isCreated());
     }
}



