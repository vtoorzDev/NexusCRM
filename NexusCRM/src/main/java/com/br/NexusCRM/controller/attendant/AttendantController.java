package com.br.NexusCRM.controller.attendant;

import com.br.NexusCRM.dto.requestDTO.attendant.AttendantRequestDTO;
import com.br.NexusCRM.dto.responseDTO.attendant.AttendantResponseDTO;
import com.br.NexusCRM.service.attendant.AttendantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listAll")
    public List<AttendantResponseDTO> listAllAttendants() {
        return attendantService.listAllAttendants();
    }

    @GetMapping("/list/{id}")
    public AttendantResponseDTO listAttendantId(@PathVariable Long id) {
        return attendantService.listAttendantId(id);
    }

    @PutMapping("/update/{id}")
    public AttendantResponseDTO updateAttendant(@PathVariable Long id,  @RequestBody @Valid  AttendantRequestDTO attendantRequestDTO ) {
        return attendantService.updateAttendant(attendantRequestDTO, id);
    }

    @PutMapping("/activate/{id}")
    public AttendantResponseDTO activateAttendant(@PathVariable Long id) {
        return attendantService.activateAttendant(id);
    }

    @PutMapping("/desactive/{id}")
    public AttendantResponseDTO desactivateAttendant(Long id) {
        return attendantService.desactivateAttendant(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAttendant(@PathVariable Long id) {
        attendantService.deleteAttendant(id);
    }
}
