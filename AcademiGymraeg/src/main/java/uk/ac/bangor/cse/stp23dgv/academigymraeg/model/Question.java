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
	private int questionId; //primary id
	private String englishNoun;
	private String welshNoun;
	private String gender; 
	
	public Question(String englishNoun, String welshNoun, String gender) {
        this.englishNoun = englishNoun;
        this.welshNoun = welshNoun;
        this.gender = gender;
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

}
