package org.env.windCatchers.models;


import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;

import org.env.windCatchers.enums.Enums.BookingStatus;
import org.env.windCatchers.enums.Enums.PaymentStatus;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@Table(name = "bookings")
public class Booking {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Activity activity;

    private BookingStatus bookingStatus; 
    private PaymentStatus paymentStatus;


    protected Booking() {}

    public Booking(User user, Activity activity, BookingStatus bookingStatus, PaymentStatus paymentStatus) {
        this.user = user;
        this.activity = activity;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
    }    
}
