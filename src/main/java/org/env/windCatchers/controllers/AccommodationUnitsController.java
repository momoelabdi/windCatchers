package org.env.windCatchers.controllers;

import org.env.windCatchers.forms.AccommodationUnits.AccommodationUnitResponseFrom;
import org.env.windCatchers.forms.AccommodationUnits.CreateAccommodationUnitFrom;
import org.env.windCatchers.services.AccommodationUnits.AccommodationUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodationUnits")
@Validated
public class AccommodationUnitsController {
    
    
    private final AccommodationUnitService accommodationUnitService;
    public AccommodationUnitsController(AccommodationUnitService accommodationUnitService) {
        this.accommodationUnitService = accommodationUnitService;
    }


    @PostMapping
    public ResponseEntity<AccommodationUnitResponseFrom> create(@RequestBody CreateAccommodationUnitFrom from) {
        AccommodationUnitResponseFrom response = accommodationUnitService.create(from);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
