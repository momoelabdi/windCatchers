package org.env.windCatchers.dtos.users;

import org.env.windCatchers.enums.Enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private UserRole role; 
    private String phone;
}
