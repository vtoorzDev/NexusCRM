package com.br.NexusCRM.repository.activity;

import com.br.NexusCRM.entity.activity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findByActivityStatus(ActivityEntity.ActivityStatus status);

    List<ActivityEntity> findByAttendantIdAndActivityStatus(Long attendantId, ActivityEntity.ActivityStatus status
    );
}
