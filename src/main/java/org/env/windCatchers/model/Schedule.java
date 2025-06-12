package org.env.windCatchers.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.env.windCatchers.enums.Enums.ScheduleStatus;

import jakarta.persistence.Id;


@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id @GeneratedValue
    private Long id;

    // @ManyToOne T0D0: 
    // private Discipline discipline;

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


    // Getters
    public Long getId() { 
        return id;
    }
    
    public LocalDateTime getStartTime() { 
        return startTime;
    }

    public LocalDateTime getEndTime() { 
        return endTime;
    }

    public String getLocation() { 
        return location;
    }

    public ScheduleStatus getStatus() { 
        return status;
    }

    // setters
    public void  setId(Long id) { 
        this.id = id;
    }
    
    public void  setStartTime(LocalDateTime startTime) { 
        this.startTime = startTime;
    }

    public void  setEndTime(LocalDateTime endTime) { 
        this.endTime = endTime;
    }

    public void  setLocation(String location) { 
        this.location = location;
    }

    public void  setStatus(ScheduleStatus status) { 
        this.status = status;
    }
}
