package com.emard.springsecurity.withProvider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.emard.springsecurity.domain.AppUser;
import com.emard.springsecurity.repo.UserRepository;

import lombok.AllArgsConstructor;

//il est prioritaire
@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        AppUser appUser = userRepository.findByUsername(username);
        if(appUser != null){
            if(passwordEncoder.matches(pwd, appUser.getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                appUser.getRoles()/*.stream() */.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
                //authorities.add(appUser.getRoles()::toString);
                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            }
        }
        throw new BadCredentialsException("No user registered with details!");
        // return null;
    }

    //ici pour dire qu'on use pwd et username
    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
