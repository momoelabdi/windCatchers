package org.env.windCatchers.controllers;
import java.util.List;
import org.env.windCatchers.dtos.accommodationUnits.AccommodationUnitResponseDTO;
import org.env.windCatchers.dtos.accommodationUnits.CreateAccommodationUnitDTO;
import org.env.windCatchers.dtos.accommodationUnits.UpdateAccommodationUnitDTO;
import org.env.windCatchers.services.AccommodationUnits.AccommodationUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodations")
@Validated
public class AccommodationUnitsController {
    
    
    private final AccommodationUnitService accommodationUnitService;
    public AccommodationUnitsController(AccommodationUnitService accommodationUnitService) {
        this.accommodationUnitService = accommodationUnitService;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationUnitResponseDTO>> getAllAccommodations() {
        List<AccommodationUnitResponseDTO> accommodations = accommodationUnitService.getAll();


        return ResponseEntity.ok(accommodations);
    }

    @PostMapping
    public ResponseEntity<AccommodationUnitResponseDTO> create(@RequestBody CreateAccommodationUnitDTO dto) {
        AccommodationUnitResponseDTO response = accommodationUnitService.create(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccommodationUnitResponseDTO> update(@PathVariable Long id, @RequestBody UpdateAccommodationUnitDTO dto) {
    
        AccommodationUnitResponseDTO response = accommodationUnitService.update(id, dto);
        
        return ResponseEntity.ok(response);
    }
}

