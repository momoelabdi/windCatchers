package org.env.windCatchers.dtos.bookings;

import org.env.windCatchers.enums.Enums.PaymentStatus;
import org.env.windCatchers.dtos.activities.ActivitiesResponseDTO;
import org.env.windCatchers.dtos.users.UserResponseDTO;
import org.env.windCatchers.enums.Enums.BookingStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public abstract class BookingsDTO {
    private Long id;
    private UserResponseDTO user;
    private ActivitiesResponseDTO activity;
    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;
}
