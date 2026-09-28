package com.br.NexusCRM.entity.client;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String companyClient;
    private LocalDateTime registrationDate = LocalDateTime.now();


    public enum ClientStatus{
        ACTIVE,
        INACTIVE
    }
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;
}
