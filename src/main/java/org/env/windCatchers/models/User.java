package org.env.windCatchers.models;
import jakarta.persistence.Id;

import org.env.windCatchers.enums.Enums.UserRole;

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
    private UserRole role; // ADMIN, INSTRUCTOR, STUDENT, GUEST
    private String phone;

    protected User() {
        // no-args constructor required by JPA spec
		// this one is protected since it should not be used directly
    }

    public User(String name, String email, String passwordHash, UserRole role, String phone) {
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

    public UserRole getRole()  {
        return role;
    }

    public String getPhone()  { 
        return phone; 
    }

    public void  setId(Long id) {
         this.id = id;
    }

    public void  setName(String name) {
          this.name = name; 
    }

    public void  setEmail(String email)  { 
         this.email = email; 
    }

    public void  setPasswordHash(String passwordHash)  { 
         this.passwordHash = passwordHash; 
    }

    public void  setRole(UserRole role)  {
         this.role = role;
    }

    public void setPhone(String phone)  { 
         this.phone = phone; 
    }
}