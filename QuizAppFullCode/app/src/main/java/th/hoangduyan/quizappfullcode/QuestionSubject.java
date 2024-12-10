package th.hoangduyan.quizappfullcode;

import java.util.List;

public class QuestionSubject {
    List<Question> questions;
    String subject;

    public QuestionSubject(List<Question> questions, String subject) {
        this.questions = questions;
        this.subject = subject;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
