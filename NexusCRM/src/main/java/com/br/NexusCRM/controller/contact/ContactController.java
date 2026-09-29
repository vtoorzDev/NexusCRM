package com.br.NexusCRM.controller.contact;

import com.br.NexusCRM.dto.requestDTO.client.ClientRequestDTO;
import com.br.NexusCRM.dto.requestDTO.contact.ContactRequestDTO;
import com.br.NexusCRM.dto.responseDTO.contact.ContactResponseDTO;
import com.br.NexusCRM.service.contact.ContactService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/register/{clientId}")
    public ContactResponseDTO registerContact(@PathVariable Long clientId, @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        return contactService.registerContact(contactRequestDTO, clientId);
    }

    @GetMapping("/listAll")
    public List<ContactResponseDTO> listingContacts() {
        return contactService.listingContact();
    }

    @GetMapping("/findId/{id}")
    public ContactResponseDTO findById(@PathVariable Long id){
        return contactService.listingContactsForId(id);
    }
}
