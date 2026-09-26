package com.br.NexusCRM.service.attendant;

import com.br.NexusCRM.repository.attendant.AttendantRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendantService {
    private final AttendantRepository attendantRepository;

    public AttendantService(AttendantRepository attendantRepository) {
        this.attendantRepository = attendantRepository;
    }
}
