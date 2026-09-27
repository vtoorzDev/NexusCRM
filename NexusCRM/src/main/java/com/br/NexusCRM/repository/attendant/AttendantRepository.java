package com.br.NexusCRM.repository.attendant;

import com.br.NexusCRM.entity.attendant.AttendantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendantRepository extends JpaRepository<AttendantEntity, Long> {
}
