package com.br.NexusCRM.controller.clients;

import com.br.NexusCRM.dto.requestDTO.client.ClientRequestDTO;
import com.br.NexusCRM.dto.responseDTO.client.ClientResponseDTO;
import com.br.NexusCRM.service.client.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ClientResponseDTO registerClient (@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.registerClient(clientRequestDTO);
    }

    @GetMapping("/listAll")
        public List<ClientResponseDTO> listAllClients() {
            return clientService.listAllClients();
    }

    @GetMapping("/list/{id}")
    public ClientResponseDTO listById(Long id) {
        return clientService.listClientById(id);
    }

    @PutMapping("/update/{id}")
    public ClientResponseDTO updateClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Long id) {
        return clientService.updateClient(clientRequestDTO, id);
    }

    @PutMapping("/inactive/{id}")
    public ClientResponseDTO inactiveClient(@PathVariable Long id) {
        return clientService.inactiveClient(id);
    }
}
