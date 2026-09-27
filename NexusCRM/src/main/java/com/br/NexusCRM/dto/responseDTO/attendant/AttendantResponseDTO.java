package com.br.NexusCRM.dto.responseDTO.attendant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendantResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String status;
}