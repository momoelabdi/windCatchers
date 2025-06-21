package org.env.windCatchers.mappers;

import org.env.windCatchers.dtos.activities.ActivitiesResponseDTO;
import org.env.windCatchers.dtos.activities.CreateActivitiesDTO;
import org.env.windCatchers.dtos.activities.UpdateActivitiesDTO;
import org.env.windCatchers.models.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivitiesMapper {

    public Activity toEntity(CreateActivitiesDTO dto) {
        return new Activity(
            dto.getTitle(),
            dto.getDescription(),
            dto.getSportType(),
            dto.getSkillLevel(),
            dto.getPrice(),
            dto.getCapacity()
        );
    }

    public ActivitiesResponseDTO toDto(Activity activity) {
        ActivitiesResponseDTO dto = new ActivitiesResponseDTO();
        dto.setId(activity.getId());
        dto.setTitle(activity.getTitle());
        dto.setDescription(activity.getDescription());
        dto.setSportType(activity.getSportType());
        dto.setSkillLevel(activity.getSkillLevel());
        dto.setPrice(activity.getPrice());
        dto.setCapacity(activity.getCapacity());
        return dto;
    }


     public void updateEntity(UpdateActivitiesDTO dto, Activity activity) {
        activity.setTitle(dto.getTitle());
        activity.setDescription(dto.getDescription());
        activity.setSportType(dto.getSportType());
        activity.setSkillLevel(dto.getSkillLevel());
        activity.setPrice(dto.getPrice());
        activity.setCapacity(dto.getCapacity());
    }
}
