package com.emard.springsecurity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emard.springsecurity.domain.AppUser;
import com.emard.springsecurity.domain.Loans;
import com.emard.springsecurity.repo.LoanRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class LoansController {
	
	private final LoanRepository loanRepository;
	
	@PostMapping("/myLoans")
	public List<Loans> getLoanDetails(@RequestBody AppUser customer) {
		List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
		if (loans != null ) {
			return loans;
		}else {
			return null;
		}
	}

}
