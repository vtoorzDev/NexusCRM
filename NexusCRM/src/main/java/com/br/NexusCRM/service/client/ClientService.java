package com.br.NexusCRM.service.client;

import com.br.NexusCRM.repository.client.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
}
