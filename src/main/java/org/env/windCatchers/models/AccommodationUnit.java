package org.env.windCatchers.models;
import jakarta.persistence.GeneratedValue;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accommodation_unit")
@Getter
@Setter
public class AccommodationUnit {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String roomType; // dorm, private, suite
    private Integer maxGuests;
    private String amenities;
    private BigDecimal pricePerNight;
    
    protected AccommodationUnit() {}


    public AccommodationUnit(String name, String roomType, Integer maxGuests, String amenities, BigDecimal pricePerNight) {
        this.name = name;
        this.roomType = roomType;
        this.maxGuests = maxGuests;
        this.amenities = amenities;        
        this.pricePerNight = pricePerNight;
    }
}
