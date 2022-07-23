package com.emard.springsecurity.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.emard.springsecurity.filter.AuthoritiesLoggingAfterFilter;
import com.emard.springsecurity.filter.AuthoritiesLoggingAtFilter;
import com.emard.springsecurity.filter.JWTTokenGeneratorFilter;
import com.emard.springsecurity.filter.JWTTokenValidatorFilter;
import com.emard.springsecurity.filter.RequestValidationBeforeFilter;

//import lombok.AllArgsConstructor;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
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
        http
        // pour dire a spring sec de ne pas gérer la session (il ne vas creer de token comme JSessionId)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                // pour ajouter au header une balise authorization avec le token
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        })
        .and()
        //.csrf().disable()//par defaul mais pas conseillé coté sécurité
        .csrf().disable()
        //pas besoin de csrf car avec jwt il y a un controle qui est fait
        //.ignoringAntMatchers("/contact")//pas forcement conecte donc pas de xsrf a envoyé pour au backend 
        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
        //le filter sera executer avant BasicAuthenticationFilter (usrdetailService) donc desactiver AuthenticationProvider 
        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
        //sexec apres Basic...
        .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
        //add filter jwt
        .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        // spring secu va choisir de maniére aléattoire lequel exec le 1er a chaqu fois
        .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests()
                .antMatchers("/myAccount").hasRole("ADMIN")//sans prefix
                .antMatchers("/myBalance").hasRole("USER")
                .antMatchers("/myLoans").authenticated()
                .antMatchers("/myCards").hasAuthority("ROLE_USER")
                .antMatchers( "/user").authenticated()
                // .anyRequest().permitAll()
                .antMatchers("/notices", "/contact", "/login").permitAll()
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
