package uk.ac.bangor.cse.stp23dgv.academigymraeg.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * Represents a User entity for the application
 * 
 * @author Steph Parry
 */

public class User implements UserDetails {
	@Id
	@NotBlank
	@NotNull
	@Size(max=255)
	@Email
	@Column(nullable = false, updatable = false)
	private String username;
	
	@NotNull
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean admin = false;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority>perms = new LinkedList<GrantedAuthority>();
		perms.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(admin)
			perms.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return perms;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
