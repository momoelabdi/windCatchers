package org.env.windCatchers.dtos.roomBooking;

import java.time.LocalDate;

import org.env.windCatchers.dtos.accommodationUnits.AccommodationUnitResponseDTO;
import org.env.windCatchers.dtos.users.UserResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBookingResponseDTO {

    private Long id;
    private UserResponseDTO user;
    private AccommodationUnitResponseDTO accommodationUnit;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer guestsCount;
    private String bookingStatus;
    private String paymentStatus;


}
