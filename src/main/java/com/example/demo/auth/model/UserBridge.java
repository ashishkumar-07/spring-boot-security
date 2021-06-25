package com.example.demo.auth.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.auth.ApplicationUserPermission;
import com.example.demo.auth.dao.User;

public class UserBridge extends User implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	public UserBridge(User user){
		super(user);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> permissions = new HashSet<>();
		
		if(this.username.equals("John")) {
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.DEVICE_CAMERA_READ.name()));
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.DEVICE_CAMERA_WRITE.name()));
			
		}
		else if(this.username.equals("Josh")) {
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.USER_READ.name()));
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.USER_WRITE.name()));
		}
		else if (this.username.equals("Janardhan"))	{
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.USER_READ.name()));
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.USER_WRITE.name()));
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.DEVICE_CAMERA_READ.name()));
			permissions.add(new SimpleGrantedAuthority(ApplicationUserPermission.DEVICE_CAMERA_WRITE.name()));
		}
		
		
		return permissions;
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
		return true;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public String getTenant(){
		return this.tenant;
	}
}
