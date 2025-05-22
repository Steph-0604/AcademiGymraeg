package uk.ac.bangor.cse.stp23dgv.academigymraeg.repo;
/**
 * UserRepository interface for accessing User data.
 * 
 * Created by Steph Parry.
 */

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
}
