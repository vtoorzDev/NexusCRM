package com.br.NexusCRM.service.contact;

import com.br.NexusCRM.repository.contact.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
