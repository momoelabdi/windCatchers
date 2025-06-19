package org.env.windCatchers.dtos.activities;

import java.math.BigDecimal;

import org.env.windCatchers.enums.Enums.SkillLevel;
import org.env.windCatchers.enums.Enums.SportType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateActivitiesDTO  {

    private Long id;
    
    @NotNull(message = "Activity title is required")
    private String title;

    @NotNull(message = "Activity description is required")
    private String description;

    @NotNull(message = "Activity Type is required")
    private SportType sportType; 

    @NotNull(message = "Activity level is required")
    private SkillLevel skillLevel;

    @NotNull(message = "Activity price is required")
    private BigDecimal price;

    private Integer capacity;
}
