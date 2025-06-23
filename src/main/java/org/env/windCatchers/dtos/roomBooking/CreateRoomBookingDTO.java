package org.env.windCatchers.dtos.roomBooking;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateRoomBookingDTO {
    
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Accommodation Unit ID is required")
    private Long accommodationUnitId;

    @FutureOrPresent(message = "Check-in date must be today or in the future")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date is required")
    private LocalDate checkOut;

    @NotNull(message = "Guest count is required")
    @Min(value = 1, message = "There must be at least 1 guest")
    private Integer guestsCount;
    
    private String bookingStatus;
    private String paymentStatus;
}
