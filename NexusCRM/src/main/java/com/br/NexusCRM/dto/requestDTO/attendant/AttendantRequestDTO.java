package com.br.NexusCRM.dto.requestDTO.attendant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendantRequestDTO {

    @NotBlank(message = "The name field must be filled in.")
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank(message = "The email field must be filled in.")
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank(message = "The name field must be filled in.")
    @Size(min = 2, max = 100)
    private String role;
}