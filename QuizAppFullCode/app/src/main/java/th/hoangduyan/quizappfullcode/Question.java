package th.hoangduyan.quizappfullcode;

import java.util.ArrayList;

public class Question {
    private String questionTxt;
    private String correctAnswer;
    private ArrayList<String> wrongAnswers;

    public Question(String questionTxt, String correctAnswer, ArrayList<String> wrongAnswers) {
        this.questionTxt = questionTxt;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public String getQuestionText() {
        return questionTxt;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }

}
