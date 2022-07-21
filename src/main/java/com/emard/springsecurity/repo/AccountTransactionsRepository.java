package com.emard.springsecurity.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.emard.springsecurity.domain.AccountTransactions;

//@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(Long customerId);
}
