package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Question;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.QuestionRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.TestRepository;

import jakarta.validation.Valid;

/**
 * Steph's Code: 
 * - Display of the test form with dynamically loaded questions.
 * - Handling of test submissions, including answer validation and score calculation.
 * - Persistence of completed test data, including score and completion timestamp.
 * - Administrative functionality to delete all test records, restricted to users 
 *   with the 'ADMIN' role.
 */
@Controller
@RequestMapping("/academiGymraeg")
public class AcademiGymraegController {

	@Autowired
	private TestRepository testrepo;
	
	@Autowired
	private QuestionRepository questionrepo;


	@GetMapping
	public String form(Model m) {

		if (!m.containsAttribute("test")) {
			m.addAttribute("test", new Test());
	}
	
	List<Question> questions = questionrepo.findAll();
		m.addAttribute("questions", questions);

		return "form";
	}

    @PostMapping
    public String submit(@Valid Test test, BindingResult result, Model m) {

        if (result.hasErrors()) {
            m.addAttribute("test", test);
            return form(m);
        }

        int score = 0;
        List<Question> submittedQuestions = test.getQuestions();

        for (Question submitted : submittedQuestions) {
            // Gets the original question from DB to compare
            Question dbQuestion = questionrepo.findById(submitted.getQuestionId()).orElse(null);
            if (dbQuestion != null) { 
                boolean correct = dbQuestion.checkStudentAnswer(submitted.getStudentAnswer()); 
                if (correct) {
                    score++;
                }
            }
        }

        test.setScore(score);
        test.setDateCompleted(LocalDateTime.now());
        test.setCompleted(true);
        testrepo.save(test);

        m.addAttribute("message", "Test completed! Your score: " + score);
        return form(m);
    }
	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public String deletePrevious() {
		testrepo.deleteAll();
		
		return "redirect:/academiGymraeg";
		
	}
}

