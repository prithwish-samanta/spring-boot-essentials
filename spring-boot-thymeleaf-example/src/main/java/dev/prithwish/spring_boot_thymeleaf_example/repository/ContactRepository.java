package dev.prithwish.spring_boot_thymeleaf_example.repository;

import dev.prithwish.spring_boot_thymeleaf_example.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
