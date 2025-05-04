package uk.ac.bangor.cse.stp23dgv.academigymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.security.RepositoryUserDetailsServiceImpl;
/**
 * @author Steph Parry
 */
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class AcademiGymraegApplication {

    @Autowired
    private RepositoryUserDetailsServiceImpl uds; // This will inject the RepositoryUserDetailsServiceImpl

    public static void main(String[] args) {
        SpringApplication.run(AcademiGymraegApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

	@Bean
	SecurityFilterChain securitySetup(HttpSecurity http) throws Exception {
		return http.userDetailsService(uds)
				   .formLogin(form -> form.loginProcessingUrl("/login"))
				   .logout(lo -> lo.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")))
				   .build();
		
	}
	

}
