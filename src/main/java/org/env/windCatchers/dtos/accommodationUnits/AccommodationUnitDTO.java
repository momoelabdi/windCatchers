package org.env.windCatchers.dtos.accommodationUnits;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationUnitDTO {
    private Long id;
    private String name;
    private String roomType;
    private Integer maxGuests;
    private String amenities;
    private BigDecimal pricePerNight;
}