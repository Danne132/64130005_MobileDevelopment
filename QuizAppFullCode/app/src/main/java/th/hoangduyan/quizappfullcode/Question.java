package th.hoangduyan.quizappfullcode;

import java.util.ArrayList;

public class Question {
    private String questionTxt;
    private String correctAnswer;
    private ArrayList<String> answers;

    public Question(String questionTxt, String correctAnswer, ArrayList<String> answers) {
        this.questionTxt = questionTxt;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionTxt;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

}
