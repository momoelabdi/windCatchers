package org.env.windCatchers.services.bookings;

import org.env.windCatchers.forms.bookings.CreateBookingForm;
import org.env.windCatchers.forms.bookings.BookingResponseForm;
import org.env.windCatchers.mappers.BookingMapper;
import org.env.windCatchers.models.Booking;
import org.env.windCatchers.models.Schedule;
import org.env.windCatchers.models.User;
import org.env.windCatchers.repositories.BookingRepository;
import org.env.windCatchers.repositories.UserRepository;
import org.env.windCatchers.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final BookingMapper bookingMapper;


    public CreateBookingService(BookingRepository bookingRepository,
                                 UserRepository userRepository,
                                 ScheduleRepository scheduleRepository,
                                 BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.bookingMapper = bookingMapper;
    }

    public BookingResponseForm execute(CreateBookingForm form) {
        User user = userRepository.findById(form.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Schedule schedule = scheduleRepository.findById(form.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));


        Booking booking = bookingMapper.toEntity(form, user, schedule);
        Booking saveBooking = bookingRepository.save(booking);

        return bookingMapper.toDto(saveBooking);
    }
}