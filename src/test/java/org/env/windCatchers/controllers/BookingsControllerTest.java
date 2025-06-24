package org.env.windCatchers.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import org.env.windCatchers.dtos.bookings.BookingResponseDTO;
import org.env.windCatchers.dtos.bookings.CreateBookingDTO;
import org.env.windCatchers.dtos.bookings.UpdateBookingDTO;
import org.env.windCatchers.enums.Enums.BookingStatus;
import org.env.windCatchers.enums.Enums.PaymentStatus;

import org.env.windCatchers.services.bookings.BookingService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@WebMvcTest(BookingsController.class)
public class BookingsControllerTest {
    
    @Autowired
    MockMvc mvc;

    @Autowired 
    ObjectMapper mapper;

    @MockitoBean
    BookingService bookingService;


    private BookingResponseDTO mockBooking;
    private CreateBookingDTO createForm;
    private UpdateBookingDTO updateForm;


    @BeforeEach
    void setUp () {
      mockBooking = new BookingResponseDTO();
      mockBooking.setId(1L);
      mockBooking.setPaymentStatus(PaymentStatus.PAID);
      mockBooking.setBookingStatus(BookingStatus.CONFIRMED);

      createForm = new CreateBookingDTO();
      createForm.setUserId(1L);
      createForm.setActivityId(1L);
      createForm.setBookingStatus(BookingStatus.CONFIRMED);
      createForm.setPaymentStatus(PaymentStatus.PAID);

      updateForm = new UpdateBookingDTO();
      updateForm.setBookingStatus(BookingStatus.CONFIRMED);
      updateForm.setPaymentStatus(PaymentStatus.PAID);
   }

   @Test
   void shouldReturnPaginatedBookings() throws Exception {
      when(bookingService.getAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(mockBooking)));
      mvc.perform(get("/api/bookings"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.success", is(true)))
         .andExpect(jsonPath("$.data.content[0].id", is(1)));
   }

   @Test
   void shouldReturnBookingById() throws Exception {
      when(bookingService.getById(1L)).thenReturn(mockBooking);
      mvc.perform(get("/api/bookings/1"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.success", is(true)))
         .andExpect(jsonPath("$.data.id", is(1)));
   }

   @Test 
   void shouldCreateNewBooking() throws Exception {
      when(bookingService.create(any(CreateBookingDTO.class))).thenReturn(mockBooking);
      mvc.perform(post("/api/bookings")
               .contentType(MediaType.APPLICATION_JSON)
               .content(mapper.writeValueAsString(createForm)))
         .andExpect(status().isCreated())
         .andExpect(jsonPath("$.success", is(true)))
         .andExpect(jsonPath("$.data.bookingStatus", is(createForm.getBookingStatus().toString())));
   }

   @Test
   void shouldUpdateBooking() throws Exception {
      when(bookingService.update(eq(1L), any(UpdateBookingDTO.class)))
            .thenReturn(mockBooking);

      mvc.perform(put("/api/bookings/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(mapper.writeValueAsString(updateForm)))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.success", is(true)))
         .andExpect(jsonPath("$.data.bookingStatus", is(updateForm.getBookingStatus().toString())));
   }

   @Test 
   public void shouldDeleteBooking() throws Exception {
   doNothing().when(bookingService).delete(1L);
      mvc.perform(delete("/api/bookings/1"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.success", is(true)))
         .andExpect(jsonPath("$.data", is(IsNull.nullValue())));
   }
}



