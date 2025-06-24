package org.env.windCatchers.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import org.env.windCatchers.enums.Enums.ScheduleStatus;
import jakarta.persistence.Id;


@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Instructor instructor;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private ScheduleStatus status;


    protected Schedule() {}

    public  Schedule(LocalDateTime startTime, LocalDateTime endTime, String location, ScheduleStatus status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.status = status;
    }
}
