package org.env.windCatchers.mappers;

import org.env.windCatchers.dtos.accommodationUnits.AccommodationUnitResponseDTO;
import org.env.windCatchers.dtos.accommodationUnits.CreateAccommodationUnitDTO;
import org.env.windCatchers.dtos.accommodationUnits.UpdateAccommodationUnitDTO;
import org.env.windCatchers.models.AccommodationUnit;
import org.springframework.stereotype.Component;


@Component
public class AccommodationUnitMapper {

    public  AccommodationUnit toEntity(CreateAccommodationUnitDTO dto) {
        return new AccommodationUnit(
            dto.getName(),
            dto.getRoomType(),
            dto.getMaxGuests(),
            dto.getAmenities(),
            dto.getPricePerNight()
        );
    }

    public  AccommodationUnitResponseDTO toDto(AccommodationUnit entity) {
        AccommodationUnitResponseDTO dto = new AccommodationUnitResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRoomType(entity.getRoomType());
        dto.setMaxGuests(entity.getMaxGuests());
        dto.setAmenities(entity.getAmenities());
        dto.setPricePerNight(entity.getPricePerNight());
        return dto;
    }

    public void updateEntity(UpdateAccommodationUnitDTO dto, AccommodationUnit entity) {
        entity.setName(dto.getName());
        entity.setRoomType(dto.getRoomType());
        entity.setMaxGuests(dto.getMaxGuests());
        entity.setAmenities(dto.getAmenities());
        entity.setPricePerNight(dto.getPricePerNight());
    }
    
}

