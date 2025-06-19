package org.env.windCatchers.dtos.activities;

import java.math.BigDecimal;

import org.env.windCatchers.enums.Enums.SkillLevel;
import org.env.windCatchers.enums.Enums.SportType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivitiesDTO {
    
    private Long id;
    private String title;
    private String description;
    private SportType sportType; 
    private SkillLevel skillLevel;
    private BigDecimal price;
    private Integer capacity;
}
