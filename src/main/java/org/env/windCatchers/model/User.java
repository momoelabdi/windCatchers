package org.env.windCatchers.model;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "\"user\"")
public class User {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String passwordHash;
    private String role; // ADMIN, INSTRUCTOR, STUDENT, GUEST
    private String phone;

    protected User() {
        // no-args constructor required by JPA spec
		// this one is protected since it should not be used directly
    }

    public User(String name, String email, String passwordHash, String role, String phone) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.phone = phone;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
         return name; 
    }

    public String getEmail()  { 
        return email; 
    }

    public String getPasswordHash()  { 
        return passwordHash; 
    }

    public String getRole()  {
        return role;
    }

    public String getPhone()  { 
        return phone; 
    }


    // T0D0 Setters ...

}