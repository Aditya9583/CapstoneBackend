package net.guides.springboot.crud.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.guides.springboot.crud.model.User;
import net.guides.springboot.crud.repository.UserReposistory;

@Service
public class CustomeUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	private UserReposistory userReposistory;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user= userReposistory.findByusername(username);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
