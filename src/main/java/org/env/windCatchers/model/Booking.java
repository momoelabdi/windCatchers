package org.env.windCatchers.model;


import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;

import org.env.windCatchers.enums.Enums.BookingStatus;
import org.env.windCatchers.enums.Enums.PaymentStatus;
import jakarta.persistence.Entity;

@Entity
public class Booking {

     @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Schedule schedule;

    private BookingStatus bookingStatus; 
    private PaymentStatus paymentStatus;


    protected Booking() {}

    public Booking(BookingStatus bookingStatus, PaymentStatus paymentStatus) {
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
    }

    // Getters
    public BookingStatus getBookingStatus() { 
        return bookingStatus;
    }
    public PaymentStatus getPaymentStatus() { 
        return paymentStatus;
    }
    
    public User getUser() {
        return user; 
        
    }
    public Schedule getSchedule() { 
        return schedule; 
    }

    // Setters
    public void setUser(User user) {
         this.user = user; 
    }

    public void setSchedule(Schedule schedule) { 
        this.schedule = schedule; 
    }

    public void setBookingStatus(BookingStatus bookingStatus) { 
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) { 
        this.paymentStatus = paymentStatus; 
    }
    
}
