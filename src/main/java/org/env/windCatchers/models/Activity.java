package org.env.windCatchers.models;
import org.env.windCatchers.enums.Enums.SkillLevel;
import org.env.windCatchers.enums.Enums.SportType;
import jakarta.persistence.GeneratedValue;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private SportType sportType; 
    private SkillLevel skillLevel;
    private BigDecimal price;
    private Integer capacity;


    protected Activity() {}

    public Activity(String title,String description,SportType sportType,SkillLevel skillLevel,BigDecimal price, Integer capacity) {
        this.title = title;
        this.description = description;
        this.sportType = sportType;
        this.skillLevel = skillLevel;
        this.price = price;
        this.capacity = capacity;
    }
}
