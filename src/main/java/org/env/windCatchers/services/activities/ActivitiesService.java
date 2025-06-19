package org.env.windCatchers.services.activities;

import java.util.List;
import java.util.stream.Collectors;

import org.env.windCatchers.dtos.activities.ActivitiesResponseDTO;
import org.env.windCatchers.dtos.activities.CreateActivitiesDTO;
import org.env.windCatchers.dtos.activities.UpdateActivitiesDTO;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.mappers.ActivitiesMapper;
import org.env.windCatchers.models.Activity;
import org.env.windCatchers.repositories.ActivitiesRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesService {


    public final ActivitiesRepository activitiesRepository;
    private final ActivitiesMapper activitiesMapper;

    public ActivitiesService(ActivitiesRepository activitiesRepository, ActivitiesMapper activitiesMapper){
        this.activitiesRepository = activitiesRepository;
        this.activitiesMapper = activitiesMapper;
    }


    // CREATE 
    public ActivitiesResponseDTO create(CreateActivitiesDTO dto) {
        Activity activity = activitiesMapper.toEntity(dto);

        Activity savedActivity = activitiesRepository.save(activity);

        return activitiesMapper.toDto(savedActivity);
    }

    // UPDATE 
    public ActivitiesResponseDTO update(Long id, UpdateActivitiesDTO updateFrom) {
        Activity activity = activitiesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found with ID" + id ));

        activitiesMapper.updateEntity(updateFrom, activity);

        Activity updatedActivity = activitiesRepository.save(activity);

        return activitiesMapper.toDto(updatedActivity);
    }

    // GET ALL
    public List<ActivitiesResponseDTO> getAll() {
        List<Activity> activities = activitiesRepository.findAll();
        return activities.stream()
            .map(activitiesMapper::toDto)
            .collect(Collectors.toList());
    }

    // DELETE
    public void delete(Long id) {
        Activity activity = activitiesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id " + id));

        activitiesRepository.delete(activity);     
    }
}
