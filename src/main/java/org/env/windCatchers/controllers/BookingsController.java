package org.env.windCatchers.controllers;

import java.util.List;

import org.env.windCatchers.model.Booking;
import org.env.windCatchers.repository.BookingRepository;
import org.env.windCatchers.service.CreateBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private final BookingRepository bookingRepository;
    private final CreateBookingService createBookingService;

    public BookingsController(BookingRepository bookingRepository, CreateBookingService createBookingService) {
        this.bookingRepository = bookingRepository;
        this.createBookingService = createBookingService;
    }
   
    @GetMapping
    List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@Valid @RequestBody Booking booking) {
        return createBookingService.createBooking(booking);
    }
}