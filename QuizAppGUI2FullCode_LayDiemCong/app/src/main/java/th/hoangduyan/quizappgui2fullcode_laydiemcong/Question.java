package th.hoangduyan.quizappgui2fullcode_laydiemcong;

import androidx.annotation.Nullable;

import java.util.List;

public class Question {
    String question, correctAnswer;
    List<String> answers;
    int image;
    boolean isTrue;

    public Question(String question, String correctAnswer, List<String> answers, int image) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.image = image;
        isTrue = false;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
