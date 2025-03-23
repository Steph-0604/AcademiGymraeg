package uk.ac.bangor.cse.stp23dgv.academigymraeg.model;


public class Question {
	private int Id;
	private String questionText;
	private String questionType; //Gender, Meaning, Translation
	private String correctAnswer;
	
	public Question(String questionText, String questionType, String correctAnswer) {
        this.questionText = questionText;
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
	public String getQuestionText() {
        return questionText;
    }
	public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
	public String getQuestionType() {
        return questionType;
    }
	public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

	
	

}
