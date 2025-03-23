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
	private String questionType; //Gender, Meaning, Translation
	private String correctAnswer;
	
	public Question(String englishNoun, String welshNoun, String questionType, String correctAnswer) {
        this.englishNoun = englishNoun;
        this.welshNoun = welshNoun;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
    }
	
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
		return WelshNoun;
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
