package com.br.NexusCRM.service.client;

import com.br.NexusCRM.dto.requestDTO.client.ClientRequestDTO;
import com.br.NexusCRM.dto.responseDTO.client.ClientResponseDTO;
import com.br.NexusCRM.entity.client.ClientEntity;
import com.br.NexusCRM.exceptions.client.ClientException;
import com.br.NexusCRM.repository.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    private ClientResponseDTO transformResponse(ClientEntity clientEntity) {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();

        clientResponseDTO.setId(clientEntity.getId());
        clientResponseDTO.setName(clientEntity.getName());
        clientResponseDTO.setPhone(clientEntity.getPhone());
        clientResponseDTO.setEmail(clientEntity.getEmail());
        clientResponseDTO.setStatus(clientEntity.getClientStatus().name());
        clientResponseDTO.setRegistrationDate(clientEntity.getRegistrationDate());
        clientResponseDTO.setCompanyClient(clientEntity.getCompanyClient());

        return clientResponseDTO;
    }

    public ClientResponseDTO registerClient(ClientRequestDTO clientRequestDTO) {
        if (clientRepository.existsByEmail(clientRequestDTO.getEmail())) {
            throw new ClientException("Client is registred");
        }
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setName(clientRequestDTO.getName());
        clientEntity.setPhone(clientRequestDTO.getPhone());
        clientEntity.setEmail(clientRequestDTO.getEmail());
        clientEntity.setClientStatus(ClientEntity.ClientStatus.ACTIVE);
        clientEntity.setCompanyClient(clientRequestDTO.getCompanyClient());

        clientRepository.save(clientEntity);
        return transformResponse(clientEntity);
        }

    public List<ClientResponseDTO> listAllClients(){
        return clientRepository.findAll().stream().map(this::transformResponse).toList();
    }
}
