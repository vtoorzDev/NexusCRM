package com.br.NexusCRM.dto.requestDTO.attendant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendantRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(min = 2, max = 100)
    private String role;
}