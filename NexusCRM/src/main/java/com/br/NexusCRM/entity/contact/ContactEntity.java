package com.br.NexusCRM.entity.contact;

import com.br.NexusCRM.entity.client.ClientEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String description;
    private LocalDate contactDate = LocalDate.now();

    private enum ContactStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
    @Enumerated(EnumType.STRING)
    private ContactStatus contactStatus;


    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
}
