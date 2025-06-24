package org.env.windCatchers.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
public class RoomBooking {
     @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private AccommodationUnit accommodationUnit;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer guestsCount;
    private String bookingStatus;
    private String paymentStatus;


    protected RoomBooking() {}
    
    public RoomBooking(User user,AccommodationUnit accommodationUnit,LocalDate checkIn,LocalDate checkOut,Integer guestsCount,String bookingStatus,String paymentStatus) {
        this.user = user;
        this.accommodationUnit = accommodationUnit;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestsCount = guestsCount;
        this.bookingStatus = bookingStatus;
    }
}



