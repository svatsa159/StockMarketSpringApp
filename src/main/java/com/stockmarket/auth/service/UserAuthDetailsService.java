package com.stockmarket.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stockmarket.auth.entity.User;
import com.stockmarket.auth.models.UserPrincipal;
import com.stockmarket.auth.repository.UserRepository;

@Service
public class UserAuthDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository usr;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usr.findByUsername(username);
		return UserPrincipal.create(user);
//		return null;
	}

}
