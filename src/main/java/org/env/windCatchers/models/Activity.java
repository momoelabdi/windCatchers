package org.env.windCatchers.models;
import org.env.windCatchers.enums.Enums.SkillLevel;
import org.env.windCatchers.enums.Enums.SportType;
import jakarta.persistence.GeneratedValue;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;


@Entity
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

    public Activity(String title,String description,SportType sportType,SkillLevel skillLevel,BigDecimal price,int capacity) {
        this.title = title;
        this.description = description;
        this.sportType = sportType;
        this.skillLevel = skillLevel;
        this.price = price;
        this.capacity = capacity;
    }

    
    public Long getId() {
         return id;
    }
    public void setId(Long id) {
         this.id = id; 
    }
    public String getTitle() {
         return title;
    }
    public void setTitle(String title) {
         this.title = title;
    }
    public String getDescription() {
         return description;
    }
    public void setDescription(String description) {
         this.description = description;
    }
    public SportType getSportType() {
         return sportType;
    }
    public void setSportType(SportType sportType) {
         this.sportType = sportType;
    }
    public SkillLevel getSkillLevel() {
         return skillLevel;
    }
    public void setSkillLevel(SkillLevel skillLevel) {
         this.skillLevel = skillLevel;
    }
    public BigDecimal getPrice() {
         return price;
    }
    public void setPrice(BigDecimal price) {
         this.price = price;
    }
    public Integer getCapacity() {
         return capacity;
    }
    public void setCapacity(Integer capacity) {
         this.capacity = capacity;
    }
}
