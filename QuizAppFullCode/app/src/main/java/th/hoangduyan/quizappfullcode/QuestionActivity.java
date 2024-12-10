package th.hoangduyan.quizappfullcode;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    ImageView logo;
    TextView questionBoard, answerATxt, answerBTxt, answerCTxt, answerDTxt;
    Button nextBtn;
    CardView answerA, answerB, answerC, answerD;
    int logoPath, currentQuestionIndex;
    List<Question> cQuest, jQuest, pQuest;
    QuestionSubject cSubject, jSubject, pSubject;

    private void getControl(){
        logo = findViewById(R.id.logo);
        questionBoard = findViewById(R.id.questionBoard);
        answerATxt = findViewById(R.id.answerATxt);
        answerBTxt = findViewById(R.id.answerBTxt);
        answerCTxt = findViewById(R.id.answerCTxt);
        answerDTxt = findViewById(R.id.answerDTxt);
        nextBtn = findViewById(R.id.nextBtn);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        cQuest = new ArrayList<>(Arrays.asList(
                new Question("Phần mở rộng của file C++ là gì?", ".cpp", new ArrayList<>(Arrays.asList("c", ".cpp", ".java", ".py"))),
                new Question("Câu lệnh để khai báo biến nguyên trong C++ là gì?", "int", new ArrayList<>(Arrays.asList("str", "float", "int", "char"))),
                new Question("Phương pháp nào trong C++ dùng để gán giá trị cho một biến?", "=", new ArrayList<>(Arrays.asList("+", "==", "=", "*"))),
                new Question("Hàm nào được sử dụng để khởi tạo đối tượng trong C++?", "constructor", new ArrayList<>(Arrays.asList("method", "operator", "function", "constructor")))
        ));
        jQuest = new ArrayList<>(Arrays.asList(
                new Question("Phần mở rộng của file Python là gì?", ".py", new ArrayList<>(Arrays.asList(".java", ".cpp", ".py", ".c"))),
                new Question("Hàm nào dùng để đọc từ một file trong Python?", "open", new ArrayList<>(Arrays.asList("read", "write", "open", "close"))),
                new Question("Câu lệnh nào dùng để định nghĩa một hàm trong Python?", "def", new ArrayList<>(Arrays.asList("func", "def", "method", "lambda"))),
                new Question("Phương thức nào không có trong Python?", "new", new ArrayList<>(Arrays.asList("open", "write", "read", "new")))
        ));
        pQuest = new ArrayList<>(Arrays.asList(
                new Question("Phần mở rộng của file Java là gì?", ".java", new ArrayList<>(Arrays.asList(".cpp", ".py", ".java", ".c"))),
                new Question("Lớp nào được sử dụng để nhập các thư viện trong Java?", "import", new ArrayList<>(Arrays.asList("include", "use", "import", "require"))),
                new Question("Câu lệnh nào dùng để tạo một đối tượng mới từ một lớp trong Java?", "new", new ArrayList<>(Arrays.asList("create", "initialize", "new", "define"))),
                new Question("Hàm nào không có trong Java?", "main", new ArrayList<>(Arrays.asList("main", "run", "start", "execute")))
        ));
        cSubject = new QuestionSubject(cQuest, "C++");
        jSubject = new QuestionSubject(jQuest, "Java");
        pSubject = new QuestionSubject(pQuest, "Python");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        getControl();
        logoPath = getIntent().getIntExtra("logo", -1);
        if(logoPath!=-1){
            logo.setImageResource(logoPath);
        }
        String subject;
        if(logoPath == R.drawable.cpp)
            subject = "C++";
        else if (logoPath == R.drawable.java)
            subject = "Java";
        else
            subject = "Python";

    }
    void displayQuestion(String subject){
        List<Question> questions;
        if (subject.equals("C++")) {
            questions = cSubject.getQuestions();
        } else if (subject.equals("Java")) {
            questions = jSubject.getQuestions();
        } else {
            questions = pSubject.getQuestions();
        }

        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionBoard.setText(currentQuestion.getQuestionText());

            // Shuffle wrong answers
            List<String> allAnswers = new ArrayList<>(currentQuestion.getWrongAnswers());
            allAnswers.add(currentQuestion.getCorrectAnswer());
            Collections.shuffle(allAnswers);

            answerATxt.setText(allAnswers.get(0));
            answerBTxt.setText(allAnswers.get(1));
            answerCTxt.setText(allAnswers.get(2));
            answerDTxt.setText(allAnswers.get(3));

            answerA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(questions, 0);
                }
            });
            answerB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(questions, 1);
                }
            });
            answerC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(questions, 2);
                }
            });
            answerD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(questions, 3);
                }
            });
        } else {
            // Handle end of questions
        }
    }

    void checkAnswer(List<Question> questions, int selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion. == selectedOption) {
            // Correct answer
        } else {
            // Wrong answer
        }
        currentQuestionIndex++;
        displayQuestion(questions);
    }
}