package com.emard.springsecurity.controller;

import java.sql.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emard.springsecurity.domain.Contact;
import com.emard.springsecurity.repo.ContactRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ContactController {
	
	private final ContactRepository contactRepository;
	
	@PostMapping("/contact")
	//@PreFilter("filterObject.contactName == 'Test'") //filterObject est le param recu ici contact
	public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
		contact.setContactId(getServiceReqNumber());
		contact.setCreateDt(new Date(System.currentTimeMillis()));
		return contactRepository.save(contact);
	}

	public String getServiceReqNumber() {
	    Random random = new Random();
	    int ranNum = random.nextInt(999999999 - 9999) + 9999;
	    return "SR"+ranNum;
	}

}

