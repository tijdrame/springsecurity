package com.emard.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emard.springsecurity.domain.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}

