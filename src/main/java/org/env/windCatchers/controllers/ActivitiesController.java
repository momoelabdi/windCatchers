package org.env.windCatchers.controllers;

import java.util.List;

import org.env.windCatchers.dtos.activities.ActivitiesResponseDTO;
import org.env.windCatchers.dtos.activities.CreateActivitiesDTO;
import org.env.windCatchers.dtos.activities.UpdateActivitiesDTO;
import org.env.windCatchers.services.activities.ActivitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/activities")
public class ActivitiesController {

    private final ActivitiesService activitiesService;

    public ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }


    @GetMapping
    public ResponseEntity<List<ActivitiesResponseDTO>> getAllActivities() {
        List<ActivitiesResponseDTO> activities = activitiesService.getAll();

        return ResponseEntity.ok(activities);
    }

    @PostMapping
    public ResponseEntity<ActivitiesResponseDTO> create(@RequestBody CreateActivitiesDTO dto) {
       ActivitiesResponseDTO createdActivity = activitiesService.create(dto);

       return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesResponseDTO> update(@PathVariable Long id,  @RequestBody UpdateActivitiesDTO dto) {
       ActivitiesResponseDTO updatedActivity = activitiesService.update(id, dto);

       return ResponseEntity.ok(updatedActivity);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        activitiesService.delete(id);
    }
}
