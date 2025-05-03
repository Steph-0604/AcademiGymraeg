package uk.ac.bangor.cse.stp23dgv.academigymraeg.model;
import jakarta.persistence.*;
/**
 * Represents a Question entity for the application
 * 
 * @author Grishma , Harry
 */
@Entity
public class Question {
    
	public static final String TYPE_GENDER = "GENDER";
    public static final String TYPE_MEANING = "MEANING";
    public static final String TYPE_TRANSLATION_TO_WELSH = "TRANSLATION_TO_WELSH";
    public static final String[] ALL_TYPES = {
        TYPE_GENDER,
        TYPE_MEANING,
        TYPE_TRANSLATION_TO_WELSH
    };
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    private String englishNoun;
    private String welshNoun;
    private String gender;

    private String questionType; 

    private String studentAnswer;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    public Question() {}

    public Question(String englishNoun, String welshNoun, String gender, String questionType) {
        this.englishNoun = englishNoun;
        this.welshNoun = welshNoun;
        this.gender = gender;
        this.questionType = questionType;
    }
    /**
     * Steph's code
	 * - Compares submitted answer with the correct answer based on question type.
	 * - Updates the 'correct' field and returns the result.
     */
    public boolean checkStudentAnswer(String submittedAnswer) {
        boolean result = false;

        switch (this.questionType) {
            case TYPE_GENDER:
                result = submittedAnswer != null && submittedAnswer.equalsIgnoreCase(this.gender);
                break;
            case TYPE_MEANING:
                result = submittedAnswer != null && submittedAnswer.equalsIgnoreCase(this.englishNoun);
                break;
            case TYPE_TRANSLATION_TO_WELSH:
                result = submittedAnswer != null && submittedAnswer.equalsIgnoreCase(this.welshNoun);
                break;
            default:
                result = false;
        }

        this.correct = result;
        return result;
    }


	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getEnglishNoun() {
		return englishNoun;
	}

	public void setEnglishNoun(String englishNoun) {
		this.englishNoun = englishNoun;
	}

	public String getWelshNoun() {
		return welshNoun;
	}

	public void setWelshNoun(String welshNoun) {
		this.welshNoun = welshNoun;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}

