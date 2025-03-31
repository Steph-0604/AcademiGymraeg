package uk.ac.bangor.cse.stp23dgv.academigymraeg.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
