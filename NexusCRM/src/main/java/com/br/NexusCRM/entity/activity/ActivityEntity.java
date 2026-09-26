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
@Table(name = "activitys")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String title;
   private String description;
   private LocalDate dueDate;
   private enum ActivityStatus{
       PENDING,
       COMPLETED,
       CANCELED
   }
   @Enumerated(EnumType.STRING)
   private ActivityEntity activityEntity;
   
   private ClientEntity client;
   private AttendantEntity attendant;

}
