package com.emard.springsecurity.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emard.springsecurity.domain.AppUser;
import com.emard.springsecurity.repo.UserRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
public class LoginController {

	private final UserRepository userRepository;
	
	@RequestMapping("/user")
	public AppUser getUserDetailsAfterLogin(Principal user) {
		AppUser appUser = userRepository.findByUsername(user.getName());
		if (appUser!=null) {
			return appUser;
		}else {
			return null;
		}
		
	}

}