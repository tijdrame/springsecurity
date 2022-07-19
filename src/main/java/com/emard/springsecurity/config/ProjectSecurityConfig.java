package com.emard.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

//import lombok.AllArgsConstructor;

@Configuration
//@AllArgsConstructor
public class ProjectSecurityConfig {
    //private final UserDetailsService userDetailsService;
    //definition du Bean dans la classe main
    //private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * Default configurations which will secure all the requests
         */
        /*
         * http .authorizeRequests() .anyRequest().authenticated() .and()
         * .formLogin().and() .httpBasic();
         */

        /**
         * Custom configurations as per our requirement
         */
        http.authorizeRequests()
                .antMatchers("/myAccount").authenticated()
                .antMatchers("/myBalance").authenticated()
                .antMatchers("/myLoans").authenticated()
                .antMatchers("/myCards").authenticated()
                // .anyRequest().permitAll()
                .antMatchers("/notices", "/contact").permitAll()
                // .antMatchers("/contact").permitAll()
                .and().formLogin().and().httpBasic();
        /**
         * Configuration to deny all the requests
         */
        /*
         * http .authorizeRequests() .anyRequest().denyAll() .and() .formLogin().and()
         * .httpBasic();
         */

        /**
         * Configuration to permit all the requests
         */
        /*
         * http .authorizeRequests() .anyRequest().permitAll().and() .formLogin().and()
         * .httpBasic();
         */
        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }



    /*@Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    public JdbcUserDetailsManager userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

        //JdbcUserDetailsManager
        /*UserDetails user = User.
        withDefaultPasswordEncoder()
            .username("tij").password("passer").roles("USER")
            .username("helgi").password("passer").roles("USER")
            .build();
            
        return null;

        //return new InMemoryUserDetailsManager(user);
    }*/

    
}
