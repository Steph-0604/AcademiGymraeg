package uk.ac.bangor.cse.stp23dgv.academigymraeg.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.UserRepository;
/**
 * Custom UserDetailsService implementation that loads user details from the UserRepository.
 * 
 * This class was implemented by Steph Parry.
 * 
 * It retrieves a User entity by username and returns it as a UserDetails object for Spring Security.
 * If the user is not found, it throws UsernameNotFoundException.
 * 
 * @author Steph Parry
 */
@Component
public class RepositoryUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> obj = repo.findById(username);
        if (obj.isEmpty())
            throw new UsernameNotFoundException(username);
        return obj.get();
    }
}
