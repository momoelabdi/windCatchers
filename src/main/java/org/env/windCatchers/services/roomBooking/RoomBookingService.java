package org.env.windCatchers.services.roomBooking;

import java.util.List;
import java.util.stream.Collectors;
import org.env.windCatchers.dtos.roomBooking.CreateRoomBookingDTO;
import org.env.windCatchers.dtos.roomBooking.RoomBookingResponseDTO;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.mappers.RoomBookingMapper;
import org.env.windCatchers.models.User;
import org.env.windCatchers.models.RoomBooking;
import org.env.windCatchers.models.AccommodationUnit;
import org.env.windCatchers.repositories.AccommodationUnitRepository;
import org.env.windCatchers.repositories.RoomBookingRepository;
import org.env.windCatchers.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingService {
    
    
    private final RoomBookingRepository roomBookingRepository;
    private final UserRepository userRepository;
    private final AccommodationUnitRepository accommodationUnitRepository;
    private final RoomBookingMapper roomBookingMapper;

    public RoomBookingService(
        RoomBookingRepository roomBookingRepository,
        RoomBookingMapper roomBookingMapper,
        UserRepository userRepository,
        AccommodationUnitRepository accommodationUnitRepository) {
        this.accommodationUnitRepository = accommodationUnitRepository;
        this.userRepository = userRepository;
        this.roomBookingRepository = roomBookingRepository;
        this.roomBookingMapper = roomBookingMapper; 
    }




    // GET ALL
    public List<RoomBookingResponseDTO> getAll() {
        List<RoomBooking> roomBookings = roomBookingRepository.findAll();

        return roomBookings.stream()
        .map(roomBookingMapper::toDto)
        .collect(Collectors.toList());
    }


    // CREATE 
    public RoomBookingResponseDTO create(CreateRoomBookingDTO dto) {
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + dto.getUserId()));
        
        AccommodationUnit accommodation = accommodationUnitRepository.findById(dto.getAccommodationUnitId())
        .orElseThrow(() -> new ResourceNotFoundException("Accommodation Unit not found with ID " + dto.getAccommodationUnitId()));


        RoomBooking roomBooking = roomBookingMapper.toEntity(dto, user, accommodation);
        
        roomBooking = roomBookingRepository.save(roomBooking);

        return roomBookingMapper.toDto(roomBooking);
    }
}
