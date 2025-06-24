package org.env.windCatchers.models;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name =  "instructors")
public class Instructor {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private String bio;
    private String certifications;
    private String availabilitySchedule;

    
    protected Instructor() {}
    
    public Instructor(String bio, String certifications , String availabilitySchedule) {
        this.bio = bio;
        this.certifications = certifications;
        this.availabilitySchedule = availabilitySchedule;
    }

    public String getBio() { return this.bio; }
    public String getCertifications() { return this.certifications; }
    public String getAvailabilitySchedule() { return this.availabilitySchedule; }

    
}
