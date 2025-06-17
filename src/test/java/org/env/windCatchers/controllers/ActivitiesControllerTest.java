package org.env.windCatchers.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.env.windCatchers.enums.Enums.SkillLevel;
import org.env.windCatchers.enums.Enums.SportType;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
import org.env.windCatchers.forms.activities.CreateActivitiesForm;
import org.env.windCatchers.forms.activities.UpdateActivitiesForm;
import org.env.windCatchers.forms.activities.ActivitiesResponseForm;
import org.env.windCatchers.services.activities.ActivitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;


@WebMvcTest(ActivitiesController.class)
public class ActivitiesControllerTest {


    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    ActivitiesService activitiesService;

    private CreateActivitiesForm createForm;
    private ActivitiesResponseForm responseForm;
    private UpdateActivitiesForm updateForm;
     
    @BeforeEach
    void setUp() {
        Faker faker = new Faker();
        createForm = new CreateActivitiesForm();
        createForm.setTitle(faker.funnyName().name());
        createForm.setDescription(faker.lorem().paragraph());
        createForm.setSkillLevel(SkillLevel.INTERMEDIATE);
        createForm.setSportType(SportType.SURFING);
        createForm.setCapacity(faker.number().randomDigit());
        createForm.setPrice(new BigDecimal(99.99));

        updateForm = new UpdateActivitiesForm();
        updateForm.setId(faker.number().randomNumber());
        updateForm.setTitle(faker.funnyName().name());
        updateForm.setDescription(faker.lorem().paragraph());
        updateForm.setSkillLevel(SkillLevel.INTERMEDIATE);
        updateForm.setSportType(SportType.SURFING);
        updateForm.setCapacity(faker.number().randomDigit());
        updateForm.setPrice(new BigDecimal(99.99));

        responseForm = new ActivitiesResponseForm();
        responseForm.setId(1L);
        responseForm.setTitle(createForm.getTitle());
        responseForm.setDescription(createForm.getDescription());
        responseForm.setSkillLevel(createForm.getSkillLevel());
        responseForm.setSportType(createForm.getSportType());
        responseForm.setCapacity(createForm.getCapacity());
        responseForm.setPrice(createForm.getPrice());
    }
    

    @Test
    void shouldReturnListOfActivities() throws Exception {
        when(activitiesService.getAll()).thenReturn(List.of(responseForm));

        mvc.perform(get("/api/activities"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data[0].id", is(responseForm.getId().intValue())))
            .andExpect(jsonPath("$.data[0].title", is(responseForm.getTitle())));
    }

    @Test
    void shouldCreateNewActivity() throws Exception {
        when(activitiesService.create(any(CreateActivitiesForm.class))).thenReturn(responseForm);

        mvc.perform(post("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createForm)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.success", is(true)));
    }


    @Test
    void shouldUpdateActivity() throws Exception {
        when(activitiesService.update(eq(updateForm.getId()) ,any(UpdateActivitiesForm.class))).thenReturn(responseForm);

        mvc.perform(put("/api/activities/{id}", updateForm.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateForm)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data.id", is(responseForm.getId().intValue())))
            .andExpect(jsonPath("$.data.title", is(responseForm.getTitle())));
    }

    
    @Test
    void updateActivityShouldReturnNotFound() throws Exception {
        Long notFoundId = 999L;
        when(activitiesService.update(eq(notFoundId) ,any(UpdateActivitiesForm.class)))
        .thenThrow(new ResourceNotFoundException("Activity not found with ID " + notFoundId));

        mvc.perform(put("/api/activities/{id}", notFoundId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateForm)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.error.message", is("Activity not found with ID " + notFoundId)));
    }

    @Test 
    void shouldDeleteActivityById() throws Exception {
        doNothing().when(activitiesService).delete(1L);
        
        mvc.perform(delete("/api/activities/{id}", 1L))
            .andExpect(status().isOk());
    }

    @Test 
    void DeleteActivityByIdShouldReturnNotFound() throws Exception {
        Long notFoundId = 999L;
        doThrow(new ResourceNotFoundException("Activity not found with ID " + notFoundId))
        .when(activitiesService).delete(notFoundId);
        
        mvc.perform(delete("/api/activities/{id}", notFoundId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.error.message", is("Activity not found with ID " + notFoundId)));
    }
}
