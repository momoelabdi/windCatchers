package org.env.windCatchers.service;

import org.env.windCatchers.model.Booking;
import org.env.windCatchers.model.Schedule;
import org.env.windCatchers.model.User;
import org.env.windCatchers.repository.BookingRepository;
import org.env.windCatchers.repository.ScheduleRepository;
import org.env.windCatchers.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class CreateBookingService {


    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public CreateBookingService(BookingRepository bookingRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }


    @Transactional
    public Booking createBooking(Booking booking) {
        
        validateRequest(booking);

        Long userId = booking.getUser().getId();
        User user = userRepository.findById(userId).get();

        Long scheduleId = booking.getSchedule().getId();
        Schedule schedule = scheduleRepository.findById(scheduleId).get();

        booking.setUser(user);
        booking.setSchedule(schedule);


        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }

     // verify if user and schedule are valid
     private void validateRequest(Booking booking) {
        if (booking.getUser() == null || booking.getUser().getId() == null) {
            throw new IllegalArgumentException("Booking must include a valid user with an ID");
        } 

        if (booking.getSchedule() == null || booking.getSchedule().getId() == null) {
            throw new IllegalArgumentException("Booking must include a valid schedule with an ID");
        }
    }
}
