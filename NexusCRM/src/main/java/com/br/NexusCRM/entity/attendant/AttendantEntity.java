package com.br.NexusCRM.entity.attendant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendants")
public class AttendantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String role;

    @Enumerated(EnumType.STRING)
    private AttendantStatus status;

    private enum AttendantStatus {
        ACTIVE,
        INACTIVE
    }
}