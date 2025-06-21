


package org.env.windCatchers.mappers;

import org.springframework.stereotype.Component;
import org.env.windCatchers.dtos.bookings.BookingResponseDTO;
import org.env.windCatchers.dtos.bookings.CreateBookingDTO;
import org.env.windCatchers.dtos.bookings.UpdateBookingDTO;
import org.env.windCatchers.models.Activity;
import org.env.windCatchers.models.Booking;
import org.env.windCatchers.models.User;

@Component
public class BookingMapper {


        private final UserMapper userMapper;
        private final ActivitiesMapper activityMapper;
        
        public BookingMapper(UserMapper userMapper, ActivitiesMapper activityMapper) {
            this.userMapper = userMapper;
            this.activityMapper = activityMapper;
        }

        public Booking toEntity(CreateBookingDTO dto, User user, Activity activity) {
            return new Booking(
            user,
            activity,
            dto.getBookingStatus(),
            dto.getPaymentStatus()
            );
    }

    public BookingResponseDTO toDto(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setUser(userMapper.toDto(booking.getUser()));
        dto.setActivity(activityMapper.toDto(booking.getActivity()));
        dto.setBookingStatus(booking.getBookingStatus());
        dto.setPaymentStatus(booking.getPaymentStatus());
        return dto;
    }


    public void updateEntity(UpdateBookingDTO dto, Booking booking, User user, Activity activity) {
        booking.setUser(user); 
        booking.setActivity(activity);
        booking.setBookingStatus(dto.getBookingStatus());
        booking.setPaymentStatus(dto.getPaymentStatus());
    }
}

