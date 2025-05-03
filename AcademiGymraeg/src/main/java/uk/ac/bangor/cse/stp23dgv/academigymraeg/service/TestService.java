package uk.ac.bangor.cse.stp23dgv.academigymraeg.service;

import org.springframework.stereotype.Service;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.TestRepository;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final QuestionGeneratorService questionGeneratorService;

    public TestService(TestRepository testRepository, QuestionGeneratorService questionGeneratorService) {
        this.testRepository = testRepository;
        this.questionGeneratorService = questionGeneratorService;
    }

    public Test createAndSaveTestForUser(User user) {
        Test test = questionGeneratorService.generateTestForUser(user);
        return testRepository.save(test); 
    }
}
