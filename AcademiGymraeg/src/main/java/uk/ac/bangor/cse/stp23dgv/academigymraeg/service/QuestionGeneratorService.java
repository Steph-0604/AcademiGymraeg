package uk.ac.bangor.cse.stp23dgv.academigymraeg.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Noun;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Question;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Test;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.NounRepository;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;

@Service
public class QuestionGeneratorService {

    private final NounRepository nounRepository;

    private static final String[] QUESTION_TYPES = {
        "GENDER", 
        "MEANING", 
        "WELSH_NOUN"
    };

    public QuestionGeneratorService(NounRepository nounRepository) {
        this.nounRepository = nounRepository;
    }

    public Test generateTestForUser(User user) {
        List<Noun> allNouns = nounRepository.findAll();
        Collections.shuffle(allNouns);

        Test test = new Test();
        test.setUser(user);
        test.setDateCreated(LocalDateTime.now());
        test.setCompleted(false);

        List<Question> questions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20 && i < allNouns.size(); i++) {
            Noun noun = allNouns.get(i);
            String type = QUESTION_TYPES[random.nextInt(QUESTION_TYPES.length)];

            Question question = new Question(
                noun.getEnglishNoun(),
                noun.getWelshNoun(),
                noun.getGender(),
                type
            );

            question.setTest(test);
            questions.add(question);
        }

        test.setQuestions(questions);
        return test;
    }
}
