package com.br.NexusCRM.dto.responseDTO.activity;

import com.br.NexusCRM.entity.activity.ActivityEntity.ActivityStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ActivityResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private ActivityStatus activityStatus;

    private Long clientId;
    private Long attendantId;
}