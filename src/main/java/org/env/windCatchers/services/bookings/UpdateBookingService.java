package org.env.windCatchers.services.bookings;
import org.env.windCatchers.forms.bookings.BookingResponseForm;
import org.env.windCatchers.forms.bookings.UpdateBookingForm;
import org.env.windCatchers.mappers.BookingMapper;
import org.env.windCatchers.models.Booking;
import org.env.windCatchers.repositories.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public UpdateBookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;

    }

     public BookingResponseForm execute(Long bookingId, UpdateBookingForm form) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        bookingMapper.updateEntity(form, booking);

        Booking updated = bookingRepository.save(booking);
        return bookingMapper.toDto(updated);
    }
}