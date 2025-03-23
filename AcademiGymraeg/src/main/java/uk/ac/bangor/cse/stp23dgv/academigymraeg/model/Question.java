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
	private int questionId;
	private String englishNoun;
	private String welshNoun;
	private String gender; //Masculine, Feminine
	//user gets to choose three options
	private String optionA; 
	private String optionB;
	private String optionC;
	private String correctAnswer;
	
	public Question(String englishNoun, String welshNoun, String gender, String optionA, String optionB, String optionC, String correctAnswer) {
        this.englishNoun = englishNoun;
        this.welshNoun = welshNoun;
        this.gender = gender;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }
	
	//Getters and Setters
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
	public void setWelshNoun(String WelshNoun) {
		this.welshNoun = WelshNoun;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public String getCorrectAnswer() {
        return correctAnswer;
    }
	public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }	

}
