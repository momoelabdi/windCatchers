package org.env.windCatchers.services.bookings;

import org.env.windCatchers.dtos.bookings.BookingResponseDTO;
import org.env.windCatchers.dtos.bookings.CreateBookingDTO;
import org.env.windCatchers.dtos.bookings.UpdateBookingDTO;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.mappers.BookingMapper;
import org.env.windCatchers.models.Activity;
import org.env.windCatchers.models.Booking;
import org.env.windCatchers.models.User;
import org.env.windCatchers.repositories.ActivitiesRepository;
import org.env.windCatchers.repositories.BookingRepository;
import org.env.windCatchers.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ActivitiesRepository activitiesRepository;
    private final BookingMapper bookingMapper;


    public BookingService(
        BookingRepository bookingRepository,
        UserRepository userRepository,
        ActivitiesRepository activitiesRepository,
        BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.activitiesRepository = activitiesRepository;
        this.bookingMapper = bookingMapper;
    }


    // create 
    public BookingResponseDTO create(CreateBookingDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Activity activity = activitiesRepository.findById(dto.getActivityId())
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));


        Booking booking = bookingMapper.toEntity(dto, user, activity);
        Booking saveBooking = bookingRepository.save(booking);

        return bookingMapper.toDto(saveBooking);
    }

    // update 
    public BookingResponseDTO update(Long id, UpdateBookingDTO dto) {

        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Activity activity = activitiesRepository.findById(dto.getActivityId())
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));

        bookingMapper.updateEntity(dto, booking, user, activity);

        Booking updated = bookingRepository.save(booking);

        return bookingMapper.toDto(updated);
    }

    // get All 
    public Page<BookingResponseDTO> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable) 
            .map(bookingMapper::toDto);       
    }

    // get by Id 
    public BookingResponseDTO getById(Long id) {
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
