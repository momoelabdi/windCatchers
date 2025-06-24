package org.env.windCatchers.dtos.schedules;


import java.time.LocalDateTime;
import org.env.windCatchers.enums.Enums.ScheduleStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulesDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private ScheduleStatus status;
}
