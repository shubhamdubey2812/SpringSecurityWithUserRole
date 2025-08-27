package com.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.security.config.Role;
import com.security.models.User;

@Service
public class UserService {
	private List<User> users=new ArrayList<>();
	public UserService() {
		users.add(new User("Mohan","Mohan@123","mohan123@gmail.com",Role.ADMIN.toString()));
		users.add(new User("John","John@123","johan123@gmail.com",Role.USER.toString()));
		users.add(new User("Lucky","Lucky@123","lucky123@gmail.com",Role.USER.toString()));
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	public Optional<User> getUser(String username) {
	    String currentUsername = getCurrentUserDetails().getUsername();
	    if (!currentUsername.equals(username)) {
	        return Optional.empty();
	    }

	    return users.stream()
	                .filter(user -> user.getName().equals(username))
	                .findAny();
	}

	public User addUser(User user) {
		users.add(user);
		return user;
	}

	public UserDetails getCurrentUserDetails() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails) {
	        return (UserDetails) principal;
	    }
	    return null;
	}


}
