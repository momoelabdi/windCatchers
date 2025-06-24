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
import org.env.windCatchers.dtos.activities.ActivitiesResponseDTO;
import org.env.windCatchers.dtos.activities.CreateActivitiesDTO;
import org.env.windCatchers.dtos.activities.UpdateActivitiesDTO;
import org.env.windCatchers.enums.Enums.SkillLevel;
import org.env.windCatchers.enums.Enums.SportType;
import org.env.windCatchers.exceptions.ResourceNotFoundException;
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

    private CreateActivitiesDTO createDTO;
    private ActivitiesResponseDTO responseDTO;
    private UpdateActivitiesDTO updateDTO;
     
    @BeforeEach
    void setUp() {
        Faker faker = new Faker();
        createDTO = new CreateActivitiesDTO();
        createDTO.setTitle(faker.funnyName().name());
        createDTO.setDescription(faker.lorem().paragraph());
        createDTO.setSkillLevel(SkillLevel.INTERMEDIATE);
        createDTO.setSportType(SportType.SURFING);
        createDTO.setCapacity(faker.number().randomDigit());
        createDTO.setPrice(new BigDecimal(99.99));

        updateDTO = new UpdateActivitiesDTO();
        updateDTO.setId(faker.number().randomNumber());
        updateDTO.setTitle(faker.funnyName().name());
        updateDTO.setDescription(faker.lorem().paragraph());
        updateDTO.setSkillLevel(SkillLevel.INTERMEDIATE);
        updateDTO.setSportType(SportType.SURFING);
        updateDTO.setCapacity(faker.number().randomDigit());
        updateDTO.setPrice(new BigDecimal(99.99));

        responseDTO = new ActivitiesResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle(createDTO.getTitle());
        responseDTO.setDescription(createDTO.getDescription());
        responseDTO.setSkillLevel(createDTO.getSkillLevel());
        responseDTO.setSportType(createDTO.getSportType());
        responseDTO.setCapacity(createDTO.getCapacity());
        responseDTO.setPrice(createDTO.getPrice());
    }
    

    @Test
    void shouldReturnListOfActivities() throws Exception {
        when(activitiesService.getAll()).thenReturn(List.of(responseDTO));

        mvc.perform(get("/api/activities"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data[0].id", is(responseDTO.getId().intValue())))
            .andExpect(jsonPath("$.data[0].title", is(responseDTO.getTitle())));
    }

    @Test
    void shouldCreateNewActivity() throws Exception {
        when(activitiesService.create(any(CreateActivitiesDTO.class))).thenReturn(responseDTO);

        mvc.perform(post("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.success", is(true)));
    }


    @Test
    void shouldUpdateActivity() throws Exception {
        when(activitiesService.update(eq(updateDTO.getId()) ,any(UpdateActivitiesDTO.class))).thenReturn(responseDTO);

        mvc.perform(put("/api/activities/{id}", updateDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.data.id", is(responseDTO.getId().intValue())))
            .andExpect(jsonPath("$.data.title", is(responseDTO.getTitle())));
    }

    
    @Test
    void updateActivityShouldReturnNotFound() throws Exception {
        Long notFoundId = 999L;
        when(activitiesService.update(eq(notFoundId) ,any(UpdateActivitiesDTO.class)))
        .thenThrow(new ResourceNotFoundException("Activity not found with ID " + notFoundId));

        mvc.perform(put("/api/activities/{id}", notFoundId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDTO)))
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
