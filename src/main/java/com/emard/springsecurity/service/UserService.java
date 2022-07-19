package com.emard.springsecurity.service;

import java.util.List;

import com.emard.springsecurity.domain.AppUser;
import com.emard.springsecurity.domain.Role;


public interface UserService {
    
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
