package com.emard.springsecurity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emard.springsecurity.domain.AccountTransactions;
import com.emard.springsecurity.domain.AppUser;
import com.emard.springsecurity.repo.AccountTransactionsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BalanceController {
	
	private final AccountTransactionsRepository accountTransactionsRepository;
	
	@PostMapping("/myBalance")
	public List<AccountTransactions> getBalanceDetails(@RequestBody AppUser customer) {
		List<AccountTransactions> accountTransactions = accountTransactionsRepository.
				findByCustomerIdOrderByTransactionDtDesc(customer.getId());
		if (accountTransactions != null ) {
			return accountTransactions;
		}else {
			return null;
		}
	}

}
