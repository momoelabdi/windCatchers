package org.env.windCatchers.services.AccommodationUnits;

import org.env.windCatchers.forms.AccommodationUnits.AccommodationUnitResponseFrom;
import org.env.windCatchers.forms.AccommodationUnits.CreateAccommodationUnitFrom;
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

    
    public AccommodationUnitResponseFrom create(CreateAccommodationUnitFrom form) {
        AccommodationUnit accommodationUnit = accommodationUnitMapper.toEntity(form);

        AccommodationUnit  savedAccommodationUnit = accommodationUnitRepository.save(accommodationUnit);

        return accommodationUnitMapper.toDto(savedAccommodationUnit);
    }
}
