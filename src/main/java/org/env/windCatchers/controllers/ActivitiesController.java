package org.env.windCatchers.controllers;

import java.util.List;
import org.env.windCatchers.forms.activities.ActivitiesResponseForm;
import org.env.windCatchers.forms.activities.CreateActivitiesForm;
import org.env.windCatchers.forms.activities.UpdateActivitiesForm;
import org.env.windCatchers.services.activities.ActivitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<ActivitiesResponseForm>> getAllActivities() {
        List<ActivitiesResponseForm> activities = activitiesService.getAll();

        return ResponseEntity.ok(activities);
    }

    @PostMapping
    public ResponseEntity<ActivitiesResponseForm> create(@RequestBody CreateActivitiesForm form) {
       ActivitiesResponseForm createdActivity = activitiesService.create(form);

       return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesResponseForm> create(@PathVariable Long id,  @RequestBody UpdateActivitiesForm form) {
       ActivitiesResponseForm updatedActivity = activitiesService.update(id, form);

       return ResponseEntity.ok(updatedActivity);
    }

}
