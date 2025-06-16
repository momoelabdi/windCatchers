package org.env.windCatchers.services.bookings;

import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.forms.bookings.BookingResponseForm;
import org.env.windCatchers.forms.bookings.CreateBookingForm;
import org.env.windCatchers.forms.bookings.UpdateBookingForm;
import org.env.windCatchers.mappers.BookingMapper;
import org.env.windCatchers.models.Booking;
import org.env.windCatchers.models.Schedule;
import org.env.windCatchers.models.User;
import org.env.windCatchers.repositories.BookingRepository;
import org.env.windCatchers.repositories.ScheduleRepository;
import org.env.windCatchers.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final BookingMapper bookingMapper;


    public BookingService(
        BookingRepository bookingRepository,
        UserRepository userRepository,
        ScheduleRepository scheduleRepository,
        BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.bookingMapper = bookingMapper;
    }


    // create 
    public BookingResponseForm create(CreateBookingForm form) {
        User user = userRepository.findById(form.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Schedule schedule = scheduleRepository.findById(form.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));


        Booking booking = bookingMapper.toEntity(form, user, schedule);
        Booking saveBooking = bookingRepository.save(booking);

        return bookingMapper.toDto(saveBooking);
    }

    // update 
    public BookingResponseForm update(Long id, UpdateBookingForm form) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));

        bookingMapper.updateEntity(form, booking);

        Booking updated = bookingRepository.save(booking);
        return bookingMapper.toDto(updated);
    }

    // get All 
    public Page<BookingResponseForm> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable) 
            .map(bookingMapper::toDto);       
    }

    // get by Id 
    public BookingResponseForm getById(Long id) {
        Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));
        return bookingMapper.toDto(booking);
    }

    // delete  by id 
    public void delete(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }

}
