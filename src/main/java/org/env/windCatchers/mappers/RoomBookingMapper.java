package org.env.windCatchers.mappers;


import org.env.windCatchers.dtos.roomBooking.CreateRoomBookingDTO;
import org.env.windCatchers.dtos.roomBooking.RoomBookingResponseDTO;
import org.env.windCatchers.dtos.roomBooking.UpdateRoomBookingDTO;
import org.env.windCatchers.models.RoomBooking;
import org.env.windCatchers.models.User;
import org.env.windCatchers.models.AccommodationUnit;
import org.springframework.stereotype.Component;

@Component
public class RoomBookingMapper {

    private final UserMapper userMapper;
    private final AccommodationUnitMapper accommodationUnitMapper;

    public RoomBookingMapper(UserMapper userMapper, AccommodationUnitMapper accommodationUnitMapper) {
        this.accommodationUnitMapper = accommodationUnitMapper;
        this.userMapper = userMapper;
    }



    public RoomBooking toEntity(CreateRoomBookingDTO dto, User user, AccommodationUnit accommodationUnit ) {
        return new RoomBooking(
            user,
            accommodationUnit,
            dto.getCheckIn(),
            dto.getCheckOut(),
            dto.getGuestsCount(),
            dto.getBookingStatus(),
            dto.getPaymentStatus()
        );
    }


    public RoomBookingResponseDTO toDto( RoomBooking roomBooking) {

        RoomBookingResponseDTO  dto = new RoomBookingResponseDTO();
        dto.setId(roomBooking.getId());
        dto.setUser(userMapper.toDto(roomBooking.getUser()));
        dto.setAccommodationUnit(accommodationUnitMapper.toDto(roomBooking.getAccommodationUnit()));
        dto.setCheckIn(roomBooking.getCheckIn());
        dto.setCheckOut(roomBooking.getCheckOut());
        dto.setGuestsCount(roomBooking.getGuestsCount());
        dto.setBookingStatus(roomBooking.getBookingStatus());
        dto.setPaymentStatus(roomBooking.getPaymentStatus());
        return dto;
    }


    public void updateEntity(UpdateRoomBookingDTO dto, RoomBooking roomBooking, User user, AccommodationUnit accommodationUnit) {
        roomBooking.setUser(user);
        roomBooking.setAccommodationUnit(accommodationUnit);
        roomBooking.setCheckIn(dto.getCheckIn());
        roomBooking.setCheckOut(dto.getCheckOut());
        roomBooking.setGuestsCount(dto.getGuestsCount());
        roomBooking.setBookingStatus(dto.getBookingStatus());
        roomBooking.setPaymentStatus(dto.getPaymentStatus());
    }
}

