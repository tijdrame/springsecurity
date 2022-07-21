package com.emard.springsecurity.controller;

import java.util.List;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emard.springsecurity.domain.AppUser;
import com.emard.springsecurity.domain.Cards;
import com.emard.springsecurity.repo.CardsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CardsController {
	
	private final CardsRepository cardsRepository;

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody AppUser customer) {
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());
		if (cards != null ) {
			return cards;
		}else {
			return null;
		}
	}

}
