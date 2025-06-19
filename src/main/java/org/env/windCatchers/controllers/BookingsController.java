package org.env.windCatchers.controllers;
import org.env.windCatchers.dtos.bookings.BookingResponseDTO;
import org.env.windCatchers.dtos.bookings.CreateBookingDTO;
import org.env.windCatchers.dtos.bookings.UpdateBookingDTO;
import org.env.windCatchers.services.bookings.BookingService;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@Validated
@RequestMapping("/api/bookings")
public class BookingsController {

    private final BookingService bookingService;

    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
   

    @GetMapping
    public ResponseEntity<Page<BookingResponseDTO>> getAll(
        @PageableDefault(size = 20, sort = "id") Pageable pageable) {
    
        Page<BookingResponseDTO> bookings = bookingService.getAll(pageable);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public BookingResponseDTO getById(@PathVariable Long id) {
        return bookingService.getById(id);
    }


    @PostMapping
    public ResponseEntity<BookingResponseDTO> create(@Valid @RequestBody  CreateBookingDTO dto) {
       BookingResponseDTO createdBooking  = bookingService.create(dto);

       return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> update(@PathVariable Long id, @RequestBody  UpdateBookingDTO dto) {
        BookingResponseDTO updatedBooking = bookingService.update(id, dto);

        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.delete(id);
    }
}

