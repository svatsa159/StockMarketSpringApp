package com.stockmarket.auth.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stockmarket.auth.entity.User;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {
	private User user;

	public UserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return user.getConfirmed();
	}

	public long getUserId() {
		return user.getId();
	}

	public String getUserEmail() {
		return user.getEmail();
	}

	public String getUserMobileNumber() {
		return user.getMobileNo();
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getUserType()));
		return new UserPrincipal(user, authorities);
	}
}
