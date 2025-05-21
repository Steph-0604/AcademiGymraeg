package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.UserRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.service.TestService;

import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;
    private final UserRepository userRepository;

    @Autowired
    public TestController(TestService testService, UserRepository userRepository) {
        this.testService = testService;
        this.userRepository = userRepository;
    }

    @GetMapping("/generate")
    public String generateTestGet(@RequestParam String username, Model model) {
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User not found: " + username);
            return "user"; // Return to user page with error
        }

        Test createdTest = testService.createAndSaveTestForUser(userOpt.get());
        
        // Redirect to the test form
        return "redirect:/academiGymraeg";
    }
    
    @PostMapping("/generate")
    public ResponseEntity<?> generateTest(@RequestParam String username) {
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found: " + username);
        }

        Test createdTest = testService.createAndSaveTestForUser(userOpt.get());
        return ResponseEntity.ok(createdTest);
    }
}
