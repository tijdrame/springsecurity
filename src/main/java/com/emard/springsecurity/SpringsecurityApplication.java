package com.emard.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	CommandLineRunner run(UserService service){
		return args -> {
			service.saveRole(new Role(null, "ROLE_USER"));
			service.saveRole(new Role(null, "ROLE_MANAGER"));
			service.saveRole(new Role(null, "ROLE_ADMIN"));
			service.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			service.saveUser(new AppUser(null, "John Travolta", "john", "1234", new ArrayList<>()));
			service.saveUser(new AppUser(null, "Will Smith", "will", "1234", new ArrayList<>()));
			service.saveUser(new AppUser(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			service.saveUser(new AppUser(null, "Arnold Scharzeneger", "arnold", "1234", new ArrayList<>()));

			service.addRoleToUser("john", "ROLE_USER");
			service.addRoleToUser("john", "ROLE_MANAGER");
			service.addRoleToUser("will", "ROLE_MANAGER");
			service.addRoleToUser("jim", "ROLE_ADMIN");
			service.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			service.addRoleToUser("arnold", "ROLE_ADMIN");
			service.addRoleToUser("arnold", "ROLE_USER");
		};
	}*/

}
