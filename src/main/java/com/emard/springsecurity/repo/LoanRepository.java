package com.emard.springsecurity.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.emard.springsecurity.domain.Loans;


@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	
	@PreAuthorize("hasRole('Root')")
	List<Loans> findByCustomerIdOrderByStartDtDesc(Long customerId);
}
