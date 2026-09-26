package com.br.NexusCRM.entity.activity;

import com.br.NexusCRM.entity.attendant.AttendantEntity;
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
@Table(name = "activities")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String title;
   private String description;
   private LocalDate dueDate;
   public enum ActivityStatus{
       PENDING,
       COMPLETED,
       CANCELED
   }
   @Enumerated(EnumType.STRING)
   private ActivityStatus activityStatus;

   @ManyToOne
   @JoinColumn(name = "client_id", nullable = false)
   private ClientEntity client;

   @ManyToOne
   @JoinColumn(name = "attendant_id", nullable = false)
   private AttendantEntity attendant;

}
