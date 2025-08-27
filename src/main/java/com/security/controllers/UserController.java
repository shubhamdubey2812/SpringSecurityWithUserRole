package com.security.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.config.Role;
import com.security.excepton.UnauthorizedException;
import com.security.models.User;
import com.security.services.UserService;

import jakarta.el.ELException;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public List<User> getAll(){
//		Collection<? extends GrantedAuthority> authorities = userService.getCurrentUserDetails().getAuthorities();
//		for (GrantedAuthority authority : authorities) {
//		    String role = authority.getAuthority();
//		}

		String role = userService.getCurrentUserDetails()
                .getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);
		if(role.replaceFirst("ROLE_","" ).equalsIgnoreCase(Role.ADMIN.toString()))
		{
			return userService.getAllUsers();
		}else {
			 return null;
		}
			
	}
	@GetMapping("/{username}")
	public Optional<User> getByUserName(@PathVariable("username") String username) {
		return userService.getUser(username);
	}
	@PostMapping("/user")
	public User adduser(@RequestBody User user) {
		return userService.addUser(user);
	}

}
