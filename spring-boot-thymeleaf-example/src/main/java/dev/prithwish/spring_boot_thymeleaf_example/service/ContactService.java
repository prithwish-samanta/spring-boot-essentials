package dev.prithwish.spring_boot_thymeleaf_example.service;

import dev.prithwish.spring_boot_thymeleaf_example.model.Contact;

public interface ContactService {
    Contact saveContact(Contact contact);
}
