package uk.ac.bangor.cse.stp23dgv.academigymraeg.model;
import jakarta.persistence.*;
/**
 * Represents a Question entity for the application
 * 
 * @author Grishma 
 */
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String englishNoun;
	private String welshNoun;
	private String gender; //Masculine, Feminine
	private String questionType; //Gender, Meaning, Translation
	private String correctAnswer;
	
	public Question(String englishNoun, String welshNoun, String gender, String questionType, String correctAnswer) {
        this.englishNoun = englishNoun;
        this.welshNoun = welshNoun;
        this.gender = gender;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
    }
	
	@ManyToOne
    @JoinColumn(name = "test_id") // Links question to a test
    private Test test;
	
	//Getters and Setters
	public int getId() {
		return Id;
	}
	public void setId(int id) {
        this.Id = Id;
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
	public void setWelshNoun(String WelshNoun) {
		this.welshNoun = WelshNoun;
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
	
	public String getCorrectAnswer() {
        return correctAnswer;
    }
	public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }	

}
