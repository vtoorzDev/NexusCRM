package com.br.NexusCRM.dto.responseDTO.contact;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ContactResponseDTO {

    private Long id;
    private String subject;
    private String description;
    private LocalDate contactDate;
    private String contactStatus;
    private Long clientId;
}