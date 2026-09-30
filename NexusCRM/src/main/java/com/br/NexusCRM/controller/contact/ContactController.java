package com.br.NexusCRM.controller.contact;

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

    @PutMapping("/update/{id}")
    public ContactResponseDTO updateContact(@PathVariable Long id, @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        return contactService.updateContact(contactRequestDTO, id);
    }

    @PutMapping("/activate/{id}")
    public ContactResponseDTO openContact(@PathVariable Long id) {
        return contactService.openContact(id);
    }

    @PutMapping("/completed/{id}")
    public ContactResponseDTO completedContact(@PathVariable Long id) {
        return contactService.CompletedContact(id);
    }

    @PutMapping("/progress/{id}")
    public ContactResponseDTO progressContact(@PathVariable Long id) {
        return contactService.progresContact(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
