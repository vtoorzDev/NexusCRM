package com.br.NexusCRM.dto.responseDTO.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String companyClient;
    private LocalDateTime registrationDate;
    private String status;
}
