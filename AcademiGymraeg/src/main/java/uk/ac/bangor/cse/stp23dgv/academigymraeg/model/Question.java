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

}
