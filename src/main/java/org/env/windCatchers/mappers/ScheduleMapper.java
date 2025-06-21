package org.env.windCatchers.mappers;

import org.env.windCatchers.dtos.schedules.ScheduleResponseDTO;
import org.env.windCatchers.models.Schedule;
import org.springframework.stereotype.Component;


@Component
public class ScheduleMapper {
     
    public ScheduleResponseDTO toDto(Schedule schedule) {
        ScheduleResponseDTO dto = new ScheduleResponseDTO();
        dto.setId(schedule.getId());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setLocation(schedule.getLocation());
        dto.setStatus(schedule.getStatus());
        return dto;
    }    
}





