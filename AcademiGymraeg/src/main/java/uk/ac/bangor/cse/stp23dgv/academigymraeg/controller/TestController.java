package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
