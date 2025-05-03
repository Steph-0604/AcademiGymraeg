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

    public User() {}

    // Getters and setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
        List<GrantedAuthority> perms = new LinkedList<>();
        perms.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (admin)
            perms.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (instructor)
            perms.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
        return perms;
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
