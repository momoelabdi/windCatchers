package org.env.windCatchers.dtos.users;

import org.env.windCatchers.enums.Enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUserDTO {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String passwordHash;
    @NotBlank
    private UserRole role;
    private String phone;
    
}
