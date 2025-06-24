package org.env.windCatchers.dtos.schedules;

import java.time.LocalDateTime;
import org.env.windCatchers.enums.Enums.ScheduleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateScheduleDTO {

    @NotNull(message = "Instructor ID is required")
    private Long instructorId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @NotBlank(message = "Location is required")
    private String location;

    private ScheduleStatus status;    
}
