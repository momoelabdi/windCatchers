package org.env.windCatchers.enums;

public class Enums {

    public enum SportType {
        SURFING,
        WINDSURFING,
        KITESURING
    }

    public enum SkillLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }
    public enum ScheduleStatus {
        OPEN,
        // STARTED,
        FULL,
        CANCELLED
    }
    public enum BookingStatus {
        CONFIRMED,
        CANCELLED
    }

    public enum PaymentStatus {
        PAID,
        PANDIG,
        CANCELLED
    }
    
    public enum UserRole {
        ADMIN,
        INSTRUCTOR,
        STUDENT,
        GUEST
    }
}
