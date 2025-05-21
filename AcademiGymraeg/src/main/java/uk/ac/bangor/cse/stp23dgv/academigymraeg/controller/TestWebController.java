package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.UserRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.service.TestService;

@Controller
@RequestMapping("/tests/web")
public class TestWebController {
    private final TestService testService;
    private final UserRepository userRepository;
    
    @Autowired
    public TestWebController(TestService testService, UserRepository userRepository) {
        this.testService = testService;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/generate")
    public String generateTestGet(Authentication authentication, Model model) {
        String username = authentication.getName();
        
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User not found: " + username);
            return "user"; 
        }

        Test createdTest = testService.createAndSaveTestForUser(userOpt.get());
        
        // Add the test to the model
        model.addAttribute("test", createdTest);
        model.addAttribute("questions", createdTest.getQuestions());
        
        return "form";  // Return the view name directly
    }
    }
