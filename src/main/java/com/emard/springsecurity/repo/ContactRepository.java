package com.emard.springsecurity.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emard.springsecurity.domain.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
