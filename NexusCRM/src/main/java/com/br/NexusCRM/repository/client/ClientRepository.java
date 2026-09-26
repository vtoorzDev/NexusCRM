package com.br.NexusCRM.repository.client;

import com.br.NexusCRM.entity.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
