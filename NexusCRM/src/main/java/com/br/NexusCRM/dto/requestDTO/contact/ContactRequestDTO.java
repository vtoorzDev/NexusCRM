package com.br.NexusCRM.dto.requestDTO.contact;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String subject;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
}