package com.br.NexusCRM.service.activity;

import com.br.NexusCRM.repository.activity.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
}
