package org.env.windCatchers.mappers;

import org.env.windCatchers.dtos.users.UserResponseDTO;
import org.env.windCatchers.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    
    public UserResponseDTO toDto(User user) {
        if (user == null ) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }
    
}
