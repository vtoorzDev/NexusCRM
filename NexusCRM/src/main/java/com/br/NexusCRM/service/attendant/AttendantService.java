package com.br.NexusCRM.service.attendant;

import com.br.NexusCRM.dto.requestDTO.attendant.AttendantRequestDTO;
import com.br.NexusCRM.dto.responseDTO.attendant.AttendantResponseDTO;
import com.br.NexusCRM.entity.attendant.AttendantEntity;
import com.br.NexusCRM.exceptions.attendant.AttendantException;
import com.br.NexusCRM.repository.attendant.AttendantRepository;
import jdk.dynalink.Operation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendantService {
    private final AttendantRepository attendantRepository;

    public AttendantService(AttendantRepository attendantRepository) {
        this.attendantRepository = attendantRepository;
    }

    private AttendantResponseDTO transformResponse(AttendantEntity attendantEntity) {
        AttendantResponseDTO attendantResponseDTO = new AttendantResponseDTO();

        attendantResponseDTO.setId(attendantEntity.getId());
        attendantResponseDTO.setName(attendantEntity.getName());
        attendantResponseDTO.setRole(attendantEntity.getRole());
        attendantResponseDTO.setStatus(attendantEntity.getStatus().name());
        attendantResponseDTO.setEmail(attendantEntity.getEmail());

        return attendantResponseDTO;
    }

    public AttendantResponseDTO registerAttendant(AttendantRequestDTO attendantRequestDTO) {
        if (attendantRepository.existsByEmail(attendantRequestDTO.getEmail())) {
            throw new AttendantException("Attendant is registered");
        }

        AttendantEntity attendantRegistered = new AttendantEntity();

        attendantRegistered.setName(attendantRequestDTO.getName());
        attendantRegistered.setRole(attendantRequestDTO.getRole());
        attendantRegistered.setStatus(AttendantEntity.AttendantStatus.ACTIVE);
        attendantRegistered.setEmail(attendantRequestDTO.getEmail());

        attendantRepository.save(attendantRegistered);

        return transformResponse(attendantRegistered);
    }

    public List<AttendantResponseDTO> listAllAttendants(){
        return attendantRepository.findAll().stream().map(this::transformResponse).toList();
    }
    public AttendantResponseDTO listAttendantId(Long id) {
        Optional<AttendantEntity> attendantFound = attendantRepository.findById(id);

        if (attendantFound.isEmpty()) {
            throw new AttendantException("Attendant not found");
        }
        return transformResponse(attendantFound.get());
    }

    public AttendantResponseDTO updateAttendant(AttendantRequestDTO attendantRequestDTO, Long id) {
       Optional<AttendantEntity> attendantFound = attendantRepository.findById(id);

       if (attendantFound.isEmpty()) {
           throw new AttendantException("Attendant not found");
       }

       AttendantEntity attendant = attendantFound.get();

       if (!attendant.getEmail().equals(attendantRequestDTO.getEmail())  && attendantRepository.existsByEmail(attendantRequestDTO.getEmail())) {
           throw new AttendantException("email is already registered");
       }

       attendant.setName(attendantRequestDTO.getName());
       attendant.setRole(attendantRequestDTO.getRole());
       attendant.setEmail(attendantRequestDTO.getEmail());

       attendantRepository.save(attendant);

       return transformResponse(attendant);
    }

    public AttendantResponseDTO activateAttendant(Long id) {
        Optional<AttendantEntity> attendantFound = attendantRepository.findById(id);

        if (attendantFound.isEmpty()) {
            throw new AttendantException("Attendent not found");
        }

        AttendantEntity attendant = attendantFound.get();

        attendant.setStatus(AttendantEntity.AttendantStatus.ACTIVE);
        attendantRepository.save(attendant);

        return transformResponse(attendant);
    }

    public AttendantResponseDTO desactivateAttendant(Long id) {
        Optional<AttendantEntity> attendantFound = attendantRepository.findById(id);

        if (attendantFound.isEmpty()) {
            throw new AttendantException("Attendant not found");
        }

        AttendantEntity attendant = attendantFound.get();

        attendant.setStatus(AttendantEntity.AttendantStatus.INACTIVE);
        attendantRepository.save(attendant);

        return transformResponse(attendant);
    }
}
