package com.br.NexusCRM.repository.attendant;

import com.br.NexusCRM.entity.attendant.AttendantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendantRepository extends JpaRepository<AttendantEntity, Long> {
    boolean existsByEmail(String email);
}
