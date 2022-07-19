package com.emard.springsecurity.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.emard.springsecurity.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}