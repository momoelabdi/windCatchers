package org.env.windCatchers.services.AccommodationUnits;

import java.util.List;
import java.util.stream.Collectors;

import org.env.windCatchers.dtos.accommodationUnits.AccommodationUnitResponseDTO;
import org.env.windCatchers.dtos.accommodationUnits.CreateAccommodationUnitDTO;
import org.env.windCatchers.dtos.accommodationUnits.UpdateAccommodationUnitDTO;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.mappers.AccommodationUnitMapper;
import org.env.windCatchers.models.AccommodationUnit;
import org.env.windCatchers.repositories.AccommodationUnitRepository;
import org.springframework.stereotype.Service;

@Service
public class AccommodationUnitService {
    
    public final AccommodationUnitRepository accommodationUnitRepository;
    public final AccommodationUnitMapper accommodationUnitMapper;

    public AccommodationUnitService(
        AccommodationUnitRepository accommodationUnitRepository,
        AccommodationUnitMapper accommodationUnitMapper
        ){
            this.accommodationUnitRepository = accommodationUnitRepository;
            this.accommodationUnitMapper = accommodationUnitMapper;
        }

    
    // GET ALL <~ to paginate ~>
    public List<AccommodationUnitResponseDTO> getAll() {

        List<AccommodationUnit> accommodations = accommodationUnitRepository.findAll();
        
        return accommodations.stream()
            .map(accommodationUnitMapper::toDto)
            .collect(Collectors.toList());
    }

    // GET /:id
    public AccommodationUnitResponseDTO getById(Long id) {

        AccommodationUnit accommodations = accommodationUnitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Accommodation Unit not found with ID: " + id));
        
        return  accommodationUnitMapper.toDto(accommodations);
    }

    // create 
    public AccommodationUnitResponseDTO create(CreateAccommodationUnitDTO dto) {
        
        AccommodationUnit accommodationUnit = accommodationUnitMapper.toEntity(dto);
        AccommodationUnit savedAccommodationUnit = accommodationUnitRepository.save(accommodationUnit);

        return accommodationUnitMapper.toDto(savedAccommodationUnit);
    }

    // Updated 
    public AccommodationUnitResponseDTO update(Long id, UpdateAccommodationUnitDTO dto) {
        AccommodationUnit accommodationUnit = accommodationUnitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Accommodation Unit not found with ID: " + id));

        accommodationUnitMapper.updateEntity(dto, accommodationUnit);
        AccommodationUnit  updatedAccommodation = accommodationUnitRepository.save(accommodationUnit);

        return  accommodationUnitMapper.toDto(updatedAccommodation);
    }

    // Delete
    public void delete(Long id) {
        AccommodationUnit activity = accommodationUnitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id " + id));

        accommodationUnitRepository.delete(activity);     
    }

}
