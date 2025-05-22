package uk.ac.bangor.cse.stp23dgv.academigymraeg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByUser_UsernameOrderByDateCreatedDesc(String username);

}
