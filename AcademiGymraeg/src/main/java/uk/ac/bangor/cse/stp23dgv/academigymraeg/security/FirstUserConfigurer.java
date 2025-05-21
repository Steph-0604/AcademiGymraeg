package uk.ac.bangor.cse.stp23dgv.academigymraeg.security;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.UserRepository;

@Component

public class FirstUserConfigurer {

		@Autowired
		private PasswordEncoder encoder;
		
		@Autowired
		private UserRepository repo;
		
		@PostConstruct
		private void setupAdmin() {
			if (!repo.existsById("admin@admin.com")) {
				User u = new User();
				u.setAdmin(true);
				u.setPassword(encoder.encode("password"));
				u.setUsername("admin@admin.com");
				
				repo.save(u);
			}
		
		 if (!repo.existsById("instructor@instructor.com")) {
		        User instructor = new User();
		        instructor.setInstructor(true);
		        instructor.setPassword(encoder.encode("password"));
		        instructor.setUsername("instructor@instructor.com");
		        repo.save(instructor);
		    }
		    
		    
		 if (!repo.existsById("user@user.com")) {
		        User user = new User();
		        user.setPassword(encoder.encode("password"));
		        user.setUsername("user@user.com");
		        repo.save(user);
		    }
		}
}

