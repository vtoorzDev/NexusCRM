package com.br.NexusCRM.controller.attendant;

import com.br.NexusCRM.dto.requestDTO.attendant.AttendantRequestDTO;
import com.br.NexusCRM.dto.responseDTO.attendant.AttendantResponseDTO;
import com.br.NexusCRM.service.attendant.AttendantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendants")
public class AttendantController {

    private final AttendantService attendantService;

    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }

    @PostMapping("/register")
    public AttendantResponseDTO registerAttendant (@Valid @RequestBody AttendantRequestDTO attendantRequestDTO) {
        return attendantService.registerAttendant(attendantRequestDTO);
    }
}
