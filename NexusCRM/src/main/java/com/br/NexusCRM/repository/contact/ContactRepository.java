package com.br.NexusCRM.repository.contact;

import com.br.NexusCRM.entity.contact.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
