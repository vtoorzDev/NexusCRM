package com.br.NexusCRM.repository.activity;

import com.br.NexusCRM.entity.activity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}
