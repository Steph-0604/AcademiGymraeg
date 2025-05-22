package uk.ac.bangor.cse.stp23dgv.academigymraeg.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * Represents a User entity for the application
 * 
 * @author Steph Parry
 *
 * Contributions by Steph Parry:
 * - Defined fields for username, password, roles (admin, instructor, student)
 * - Getters and setters for username, password, and roles
 * - Implemented getAuthorities() for role-based permissions
 * - toString() method for debugging/readability
 * 
 * Note:
 * The methods below implementing UserDetails interface for account status and enabling
 * (isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, getPassword, getUsername)
 * were added by Harry .
 */

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @NotBlank
    @NotNull
    @Size(max = 255)
    @Email
    @Column(nullable = false, updatable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin = false;

    @Column(nullable = false)
    private boolean instructor = false;

	@Column(nullable = false)
	private boolean student = false;

    /**
     * A default no-argument constructor
     */
    public User() {
    }
    
    public User(String username, String password, boolean admin, boolean instructor, boolean student) {
    	this.username = username;
    	this.password = password;
    	this.admin = admin;
    	this.instructor = instructor;
    	this.student = student;

    }

    // Getters and setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

	public boolean isStudent() {
	    return student;
	}
	
	public void setStudent(boolean student) {
	    this.student = student;
	}

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> perms = new LinkedList<GrantedAuthority>();
		perms.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (admin) {
			perms.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (instructor) {
			perms.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
		}
		return perms;
	}

	@Override
	public String toString() {
	    return "User{" +
	            "username='" + username + '\'' +
	            ", admin=" + admin +
	            ", instructor=" + instructor +
	            '}';
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
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}


