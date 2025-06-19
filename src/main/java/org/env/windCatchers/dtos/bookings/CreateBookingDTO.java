package org.env.windCatchers.dtos.bookings;
import org.env.windCatchers.enums.Enums.BookingStatus;
import org.env.windCatchers.enums.Enums.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Activity ID is required")
    private Long activityId;


    @NotNull(message = "Booking status is required")
    private BookingStatus bookingStatus;

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;
}


