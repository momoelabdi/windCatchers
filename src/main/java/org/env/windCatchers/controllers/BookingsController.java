package org.env.windCatchers.controllers;
import org.env.windCatchers.forms.bookings.BookingResponseForm;
import org.env.windCatchers.forms.bookings.CreateBookingForm;
import org.env.windCatchers.forms.bookings.UpdateBookingForm;
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
    public ResponseEntity<Page<BookingResponseForm>> getAll(
        @PageableDefault(size = 20, sort = "id") Pageable pageable) {
    
        Page<BookingResponseForm> bookings = bookingService.getAll(pageable);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public BookingResponseForm getById(@PathVariable Long id) {
        return bookingService.getById(id);
    }


    @PostMapping
    public ResponseEntity<BookingResponseForm> create(@RequestBody  CreateBookingForm form) {
       BookingResponseForm createdBooking  = bookingService.create(form);

       return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseForm> update(@PathVariable Long id, @RequestBody  UpdateBookingForm form) {
        BookingResponseForm updatedBooking = bookingService.update(id, form);

        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.delete(id);
    }
}

