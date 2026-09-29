package com.br.NexusCRM.service.contact;

import com.br.NexusCRM.dto.requestDTO.client.ClientRequestDTO;
import com.br.NexusCRM.dto.requestDTO.contact.ContactRequestDTO;
import com.br.NexusCRM.dto.responseDTO.contact.ContactResponseDTO;
import com.br.NexusCRM.entity.client.ClientEntity;
import com.br.NexusCRM.entity.contact.ContactEntity;
import com.br.NexusCRM.exceptions.client.ClientException;
import com.br.NexusCRM.exceptions.contact.ContactException;
import com.br.NexusCRM.repository.client.ClientRepository;
import com.br.NexusCRM.repository.contact.ContactRepository;
import jdk.dynalink.Operation;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;

    public ContactService(ContactRepository contactRepository, ClientRepository clientRepository) {
        this.contactRepository = contactRepository;
        this.clientRepository = clientRepository;

    }

    private ContactResponseDTO transformResponse(ContactEntity contactEntity) {
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();

        contactResponseDTO.setId(contactEntity.getId());
        contactResponseDTO.setClientId(contactEntity.getClient().getId());
        contactResponseDTO.setDescription(contactEntity.getDescription());
        contactResponseDTO.setSubject(contactEntity.getSubject());
        contactResponseDTO.setContactDate(contactEntity.getContactDate());
        contactResponseDTO.setContactStatus(contactEntity.getContactStatus().name());

        return contactResponseDTO;
    }

    public ContactResponseDTO registerContact(ContactRequestDTO contactRequestDTO, Long id) {
        Optional<ClientEntity> clientFound = clientRepository.findById(id);

        if (clientFound.isEmpty()) {
            throw new ClientException("Client not found");
        }

        ClientEntity clientEntity = clientFound.get();

        if (clientEntity.getClientStatus() == ClientEntity.ClientStatus.INACTIVE) {
            throw new ClientException("Client is inactive");
        }

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setClient(clientEntity);
        contactEntity.setContactDate(LocalDate.now());
        contactEntity.setDescription(contactRequestDTO.getDescription());
        contactEntity.setSubject(contactRequestDTO.getSubject());
        contactEntity.setContactStatus(ContactEntity.ContactStatus.OPEN);

        contactRepository.save(contactEntity);

        return transformResponse(contactEntity);
    }

    public List<ContactResponseDTO> listingContact() {
        return contactRepository.findAll().stream().map(this::transformResponse).toList();
    }

    public ContactResponseDTO listingContactsForId(Long id) {
        Optional<ContactEntity> contactFound = contactRepository.findById(id);

        if (contactFound.isEmpty()) {
            throw new ContactException("Contact not found");
        }

        return transformResponse(contactFound.get());
    }

    public ContactResponseDTO updateContact(ContactRequestDTO contactRequestDTO, Long id) {
        Optional<ContactEntity> contactFound = contactRepository.findById(id);

        if (contactFound.isEmpty()) {
            throw new ContactException("Contact does not exist");
        }

        ContactEntity contactUpdate = contactFound.get();

        contactUpdate.setDescription(contactRequestDTO.getDescription());
        contactUpdate.setSubject(contactRequestDTO.getSubject());

        contactRepository.save(contactUpdate);

        return transformResponse(contactUpdate);
    }

    public ContactResponseDTO activateContact(Long id) {
        Optional<ContactEntity> contactFound = contactRepository.findById(id);

        if (contactFound.isEmpty()) {
            throw new ContactException("Contact not found");
        }

        ContactEntity contactIsOpen = contactFound.get();

        if (contactIsOpen.getContactStatus() == ContactEntity.ContactStatus.OPEN){
            throw new ContactException("Contact is already open");
        }

        contactIsOpen.setContactStatus(ContactEntity.ContactStatus.OPEN);
        contactRepository.save(contactIsOpen);

        return transformResponse(contactIsOpen);

    }
}
