package org.env.windCatchers.controllers;
import java.util.List;
import org.env.windCatchers.forms.bookings.BookingResponseForm;
import org.env.windCatchers.forms.bookings.CreateBookingForm;
import org.env.windCatchers.forms.bookings.UpdateBookingForm;
import org.env.windCatchers.services.bookings.CreateBookingService;
import org.env.windCatchers.services.bookings.ListAllBookingsService;
import org.env.windCatchers.services.bookings.UpdateBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private final ListAllBookingsService listBookingsService;
    private final CreateBookingService createBookingService;
    private final UpdateBookingService updateBookingService;

    public BookingsController(CreateBookingService createBookingService, ListAllBookingsService listBookingsService, UpdateBookingService updateBookingService) {
        this.listBookingsService = listBookingsService;
        this.createBookingService = createBookingService;
        this.updateBookingService = updateBookingService;
    }
   

    @GetMapping
    public ResponseEntity<List<BookingResponseForm>> listAll() {
        List<BookingResponseForm> bookings = listBookingsService.execute();
        return ResponseEntity.ok(bookings);
    }


   @PostMapping
    public ResponseEntity<BookingResponseForm> create(@RequestBody @Validated CreateBookingForm form) {
       BookingResponseForm created  = createBookingService.execute(form);
       return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseForm> updateBooking(@PathVariable Long id,
                                                            @RequestBody @Validated UpdateBookingForm form) {
        form.setId(id);
        BookingResponseForm updated = updateBookingService.execute(form);
        return ResponseEntity.ok(updated);
    }
}

