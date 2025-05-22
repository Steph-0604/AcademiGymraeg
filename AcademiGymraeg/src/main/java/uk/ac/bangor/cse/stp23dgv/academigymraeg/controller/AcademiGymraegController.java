package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Question;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.QuestionRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.TestRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.UserRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.service.QuestionGeneratorService;

@Controller
@RequestMapping("/academiGymraeg")
public class AcademiGymraegController {

    @Autowired
    private TestRepository testrepo;

    @Autowired
    private QuestionRepository questionrepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionGeneratorService questionGeneratorService;

    @GetMapping
    public String form(Model m, Principal principal) {
        String username = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            // User not found, redirect to login or show error page
            return "redirect:/login";
        }

        User user = optionalUser.get();

        // Generate a new test with random questions for the user
        Test test = questionGeneratorService.generateTestForUser(user);
        test.setUser(user); 
        // Save the test so you can link questions to it in DB
        testrepo.save(test);

        // Add the test and its questions to the model for Thymeleaf binding
        m.addAttribute("test", test);
        m.addAttribute("questions", test.getQuestions());

        return "form";
    }

    @PostMapping
    public String submit(@Valid Test test, BindingResult result, Model m, Principal principal) {
        if (result.hasErrors()) {
            m.addAttribute("test", test);
            return form(m, principal);
        }

        if (test.getDateCreated() == null) {
            test.setDateCreated(LocalDateTime.now());
        }

        int score = 0;
        List<Question> submittedQuestions = test.getQuestions();

        for (Question submitted : submittedQuestions) {
            // Retrieve original question from DB to validate answers
            Question dbQuestion = questionrepo.findById(submitted.getQuestionId()).orElse(null);
            if (dbQuestion != null) {
                boolean correct = dbQuestion.checkStudentAnswer(submitted.getStudentAnswer());
                if (correct) {
                    score++;
                }
                // Update student's answer and correctness in DB question
                dbQuestion.setStudentAnswer(submitted.getStudentAnswer());
                dbQuestion.setCorrect(correct);
                questionrepo.save(dbQuestion);
            }
        }

        // Clear questions from the test to avoid potential issues saving cascade
        test.setQuestions(null);

        test.setScore(score);
        test.setDateCompleted(LocalDateTime.now());
        test.setCompleted(true);

        String username = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            m.addAttribute("error", "User not found. Please log in again.");
            return "redirect:/login";
        }

        test.setUser(optionalUser.get());
        testrepo.save(test);

        m.addAttribute("message", "Test completed! Your score: " + score);
        return "results";
    }

    @GetMapping("/history")
    public String viewTestHistory(Model m, Principal principal) {
        String username = principal.getName();
        List<Test> tests = testrepo.findByUser_UsernameOrderByDateCreatedDesc(username);
        m.addAttribute("tests", tests);
        return "testHistory";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePrevious() {
        testrepo.deleteAll();
        return "redirect:/academiGymraeg";
    }
}
