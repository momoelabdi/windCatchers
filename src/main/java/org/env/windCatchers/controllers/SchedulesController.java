package org.env.windCatchers.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.env.windCatchers.models.Schedule;
import org.env.windCatchers.repositories.ScheduleRepository;

@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {


    
    private final ScheduleRepository scheduleRepository;

    public SchedulesController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    

    @PostMapping
    public Schedule create(@Valid @RequestBody Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
