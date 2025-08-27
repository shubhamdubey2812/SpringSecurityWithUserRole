package com.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.services.UserService;

@Configuration
@EnableWebSecurity
public class BasicSecurityAuthentication {

	@Autowired
	private UserService userService;
	List<UserDetails> lisUserDetails=new ArrayList<>();
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/public/**").permitAll()
                .anyRequest()
                .authenticated()
            )
            .httpBasic(e-> Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    	for(com.security.models.User u:userService.getAllUsers()) {
    		 UserDetails user = User.builder()
    		            .username(u.getName())
    		            .password(passwordEncoder.encode(u.getPassword()))
    		            .roles(u.getRole())
    		            .build();
    		 lisUserDetails.add(user);
    	}
        return new InMemoryUserDetailsManager(lisUserDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

