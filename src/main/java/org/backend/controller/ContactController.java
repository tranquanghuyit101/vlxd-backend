package org.backend.controller;

import org.backend.model.ContactRequest;
import org.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {
    @Autowired private ContactRepository contactRepository;

    @GetMapping
    public List<ContactRequest> getAllContacts() {
        return contactRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @PostMapping
    public ContactRequest saveContact(@RequestBody ContactRequest request) {
        return contactRepository.save(request);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody String newStatus) {
        return contactRepository.findById(id).map(contact -> {
            contact.setStatus(newStatus);
            contactRepository.save(contact);
            return ResponseEntity.ok("Đã cập nhật trạng thái!");
        }).orElse(ResponseEntity.notFound().build());
    }
}
