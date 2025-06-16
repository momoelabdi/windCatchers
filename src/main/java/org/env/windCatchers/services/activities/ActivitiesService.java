package org.env.windCatchers.services.activities;

import java.util.List;
import java.util.stream.Collectors;

import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.forms.activities.ActivitiesResponseForm;
import org.env.windCatchers.forms.activities.CreateActivitiesForm;
import org.env.windCatchers.forms.activities.UpdateActivitiesForm;
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


    public ActivitiesResponseForm create(CreateActivitiesForm form) {
        Activity activity = activitiesMapper.toEntity(form);

        Activity savedActivity = activitiesRepository.save(activity);

        return activitiesMapper.toDto(savedActivity);
    }



    public ActivitiesResponseForm update(Long id, UpdateActivitiesForm updateFrom) {
        Activity activity = activitiesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found with ID" + id ));

        activitiesMapper.updateEntity(updateFrom, activity);

        Activity updatedActivity = activitiesRepository.save(activity);

        return activitiesMapper.toDto(updatedActivity);
    }

    public List<ActivitiesResponseForm> getAll() {
        List<Activity> activities = activitiesRepository.findAll();
        return activities.stream()
            .map(activitiesMapper::toDto)
            .collect(Collectors.toList());
    }
}
