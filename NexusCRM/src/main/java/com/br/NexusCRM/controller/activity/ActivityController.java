package com.br.NexusCRM.controller.activity;

import com.br.NexusCRM.dto.requestDTO.activity.ActivityRequestDTO;
import com.br.NexusCRM.dto.responseDTO.activity.ActivityResponseDTO;
import com.br.NexusCRM.service.activity.ActivityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/register/client/{clientId}/attendant/{attendantId}")
    public ActivityResponseDTO registerActivity(@RequestBody @Valid ActivityRequestDTO activityRequestDTO, @PathVariable Long clientId, @PathVariable Long attendantId) {
        return activityService.registerActivity(activityRequestDTO, clientId, attendantId);
    }

    @GetMapping("/listAll")
    public List<ActivityResponseDTO> listAtivities() {
        return activityService.listAllActivities();
    }

    @GetMapping("/listPendingActivities")
    public List<ActivityResponseDTO> listPendingActivities(){
        return activityService.listPendingActivities();
    }

    @GetMapping("/listPendingActivitiesAttendant/{attendantId}")
    public List<ActivityResponseDTO> listPendingActivitiesAttendant(@PathVariable Long attendantId) {
        return activityService.listPendingActivitiesByAttendant(attendantId);
    }
    @PutMapping("update/client/{clientId}/attendant/{attendantId}/activity/{activityId}")
    public ActivityResponseDTO updateActivity(@RequestBody @Valid ActivityRequestDTO activityRequestDTO, @PathVariable Long attendantId, @PathVariable Long clientId, @PathVariable Long activityId){
        return activityService.updateActivity(activityRequestDTO, attendantId, clientId, activityId);
    }
    @PutMapping("/completeActivity/{activityId}")
    public ActivityResponseDTO completeActivity(@PathVariable Long activityId){
        return activityService.completeActivity(activityId);
    }
    @PutMapping("/canceledActivity/{activityId}")
    public ActivityResponseDTO canceledActivity(@PathVariable Long activityId){
        return activityService.canceledActivity(activityId);
    }
    @PutMapping("/openAcitivy/{activityId}")
    public ActivityResponseDTO openActivity(@PathVariable Long activityId) {
        return activityService.openActivity(activityId);
    }
}
