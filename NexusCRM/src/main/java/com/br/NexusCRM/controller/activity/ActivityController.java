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

    @GetMapping("listAll")
    public List<ActivityResponseDTO> listAtivities() {
        return activityService.listAllActivities();
    }
}
