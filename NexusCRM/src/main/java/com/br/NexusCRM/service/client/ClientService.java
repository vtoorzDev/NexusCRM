package com.br.NexusCRM.service.client;

import com.br.NexusCRM.dto.requestDTO.client.ClientRequestDTO;
import com.br.NexusCRM.dto.responseDTO.client.ClientResponseDTO;
import com.br.NexusCRM.entity.client.ClientEntity;
import com.br.NexusCRM.exceptions.client.ClientException;
import com.br.NexusCRM.repository.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ClientResponseDTO listClientById(Long id) {
        Optional<ClientEntity> clientFound = clientRepository.findById(id);

        if (clientFound.isEmpty()){
            throw new ClientException("Client not found");
        }
        return transformResponse(clientFound.get());
    }

    public ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO, Long id) {
        Optional<ClientEntity> clientFound = clientRepository.findById(id);

        if (clientFound.isEmpty()) {
            throw new ClientException("Client not found");
        }

        ClientEntity clientEntity = clientFound.get();

        clientEntity.setName(clientRequestDTO.getName());
        clientEntity.setEmail(clientRequestDTO.getEmail());
        clientEntity.setPhone(clientRequestDTO.getPhone());
        clientEntity.setCompanyClient(clientRequestDTO.getCompanyClient());

        clientRepository.save(clientEntity);
        return transformResponse(clientEntity);
    }

    public ClientResponseDTO inactiveClient(Long id){
        Optional<ClientEntity> clientFound = clientRepository.findById(id);

        if (clientFound.isEmpty()){
            throw new ClientException("Client not found");
    }
        ClientEntity clientEntity = clientFound.get();

        clientEntity.setClientStatus(ClientEntity.ClientStatus.INACTIVE);
        clientRepository.save(clientEntity);

        return transformResponse(clientEntity);
        }

    public void deleteClient(Long id) {
        Optional<ClientEntity> clientFound = clientRepository.findById(id);

        if (clientFound.isEmpty()){
            throw new ClientException("Client not found");
        }
        ClientEntity clientEntity = clientFound.get();
        clientRepository.delete(clientEntity);
    }
}
