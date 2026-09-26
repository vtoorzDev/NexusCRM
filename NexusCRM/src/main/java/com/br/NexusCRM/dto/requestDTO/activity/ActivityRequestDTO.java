package com.br.NexusCRM.dto.requestDTO.activity;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ActivityRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String title;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDate dueDate;

    @NotNull
    private Long clientId;

    @NotNull
    private Long attendantId;
}