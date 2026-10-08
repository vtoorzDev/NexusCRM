package com.br.NexusCRM.dto.responseDTO.user;

import com.br.NexusCRM.entity.user.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
