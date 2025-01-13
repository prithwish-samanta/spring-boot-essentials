package dev.prithwish.spring_boot_thymeleaf_example.service;

import dev.prithwish.spring_boot_thymeleaf_example.model.Contact;
import dev.prithwish.spring_boot_thymeleaf_example.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact saveContact(Contact contact) {
        if (!StringUtils.hasText(contact.getName())) {
            throw new RuntimeException("Name field cannot be empty");
        }
        if (!StringUtils.hasText(contact.getEmail())) {
            throw new RuntimeException("Email field cannot be empty");
        }
        if (!StringUtils.hasText(contact.getPhoneNumber())) {
            throw new RuntimeException("Phone number field cannot be empty");
        }
        if (!StringUtils.hasText(contact.getMessage())) {
            throw new RuntimeException("Message field cannot be empty");
        }
        return contactRepository.save(contact);
    }
}