package com.br.NexusCRM.dto.requestDTO.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Email
    @NotBlank
    @Size(min = 5, max = 255)
    private String email;

    @Size(min = 11, max = 11)
    private String phone;

    @NotBlank
    @Size(min = 3, max = 255)
    private String companyClient;
}
