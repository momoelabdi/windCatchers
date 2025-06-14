package org.env.windCatchers.services.bookings;

import java.util.List;

import org.env.windCatchers.forms.bookings.BookingResponseForm;
import org.env.windCatchers.mappers.BookingMapper;
import org.env.windCatchers.repositories.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class ListAllBookingsService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public ListAllBookingsService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;

    }
    
    public List<BookingResponseForm> execute() {
        return bookingRepository.findAll().stream()
            .map(bookingMapper::toDto)
            .toList();
    }
}