package com.br.NexusCRM.service.activity;

import com.br.NexusCRM.dto.requestDTO.activity.ActivityRequestDTO;
import com.br.NexusCRM.dto.responseDTO.activity.ActivityResponseDTO;
import com.br.NexusCRM.entity.activity.ActivityEntity;
import com.br.NexusCRM.entity.attendant.AttendantEntity;
import com.br.NexusCRM.entity.client.ClientEntity;
import com.br.NexusCRM.exceptions.activity.ActivityException;
import com.br.NexusCRM.repository.activity.ActivityRepository;
import com.br.NexusCRM.repository.attendant.AttendantRepository;
import com.br.NexusCRM.repository.client.ClientRepository;
import com.br.NexusCRM.repository.contact.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ClientRepository clientRepository;
    private final AttendantRepository attendantRepository;

    public ActivityService(ActivityRepository activityRepository, ClientRepository clientRepository, AttendantRepository attendantRepository) {
        this.activityRepository = activityRepository;
        this.clientRepository = clientRepository;
        this.attendantRepository = attendantRepository;
    }

    private ActivityResponseDTO transformResponse(ActivityEntity activityEntity) {
        ActivityResponseDTO activityResponseDTO = new ActivityResponseDTO();

        activityResponseDTO.setId(activityEntity.getId());
        activityResponseDTO.setClientId(activityEntity.getClient().getId());
        activityResponseDTO.setAttendantId(activityEntity.getAttendant().getId());
        activityResponseDTO.setActivityStatus(activityEntity.getActivityStatus());
        activityResponseDTO.setDescription(activityEntity.getDescription());
        activityResponseDTO.setTitle(activityEntity.getTitle());
        activityResponseDTO.setDueDate(activityEntity.getDueDate());

        return activityResponseDTO;
    }

    public ActivityResponseDTO registerActivity(ActivityRequestDTO activityRequestDTO, Long clientId, Long attendantId) {

        Optional<ClientEntity> clientFound = clientRepository.findById(clientId);
        Optional<AttendantEntity> attendantFound = attendantRepository.findById(attendantId);

        if (clientFound.isEmpty()) {
            throw new ActivityException("Client not found");
        }

        if (attendantFound.isEmpty()) {
            throw new ActivityException("Attendant not found");
        }

        AttendantEntity attendantEntity = attendantFound.get();

        if (attendantEntity.getStatus() == AttendantEntity.AttendantStatus.INACTIVE) {
            throw new ActivityException("Attendant is inactive");
        }

        ActivityEntity activityRegistered = new ActivityEntity();

        activityRegistered.setTitle(activityRequestDTO.getTitle());
        activityRegistered.setDescription(activityRequestDTO.getDescription());
        activityRegistered.setDueDate(activityRequestDTO.getDueDate());
        activityRegistered.setActivityStatus(ActivityEntity.ActivityStatus.OPEN);
        activityRegistered.setClient(clientFound.get());
        activityRegistered.setAttendant(attendantEntity);

        activityRepository.save(activityRegistered);

        return transformResponse(activityRegistered);
    }

    public List<ActivityResponseDTO> listAllActivities(){
        return activityRepository.findAll().stream().map(this::transformResponse).toList();
    }

    public List<ActivityResponseDTO> listPendingActivities() {
        return activityRepository
                .findByActivityStatus(ActivityEntity.ActivityStatus.OPEN)
                .stream()
                .map(this::transformResponse)
                .toList();
    }

}
