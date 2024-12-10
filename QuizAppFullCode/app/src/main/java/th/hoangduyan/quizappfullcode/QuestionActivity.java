package th.hoangduyan.quizappfullcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    private int currentQuestionIndex = 0;
    private List<Question> questions = new ArrayList<>();
    private CardView answerA, answerB, answerC, answerD;
    private TextView answerATxt, answerBTxt, answerCTxt, answerDTxt, questionBoardTxt;
    private Button nextButton;
    private ImageView logo;
    private int logoPath;

    ArrayList<Question> cQuest = new ArrayList<>(Arrays.asList(
            new Question("Phần mở rộng của file C++ là gì?", ".cpp", new ArrayList<>(Arrays.asList("c", ".cpp", ".java", ".py"))),
            new Question("Câu lệnh để khai báo biến nguyên trong C++ là gì?", "int", new ArrayList<>(Arrays.asList("str", "float", "int", "char"))),
            new Question("Phương pháp nào trong C++ dùng để gán giá trị cho một biến?", "=", new ArrayList<>(Arrays.asList("+", "==", "=", "*"))),
            new Question("Hàm nào được sử dụng để khởi tạo đối tượng trong C++?", "constructor", new ArrayList<>(Arrays.asList("method", "operator", "function", "constructor")))
    ));

    ArrayList<Question> pQuest = new ArrayList<>(Arrays.asList(
            new Question("Phần mở rộng của file Python là gì?", ".py", new ArrayList<>(Arrays.asList(".java", ".cpp", ".py", ".c"))),
            new Question("Hàm nào dùng để đọc từ một file trong Python?", "open", new ArrayList<>(Arrays.asList("read", "write", "open", "close"))),
            new Question("Câu lệnh nào dùng để định nghĩa một hàm trong Python?", "def", new ArrayList<>(Arrays.asList("func", "def", "method", "lambda"))),
            new Question("Phương thức nào không có trong Python?", "new", new ArrayList<>(Arrays.asList("open", "write", "read", "new")))
    ));

    ArrayList<Question> jQuest = new ArrayList<>(Arrays.asList(
            new Question("Phần mở rộng của file Java là gì?", ".java", new ArrayList<>(Arrays.asList(".cpp", ".py", ".java", ".c"))),
            new Question("Lớp nào được sử dụng để nhập các thư viện trong Java?", "import", new ArrayList<>(Arrays.asList("include", "use", "import", "require"))),
            new Question("Câu lệnh nào dùng để tạo một đối tượng mới từ một lớp trong Java?", "new", new ArrayList<>(Arrays.asList("create", "initialize", "new", "define"))),
            new Question("Hàm nào không có trong Java?", "main", new ArrayList<>(Arrays.asList("main", "run", "start", "execute")))
    ));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        logo = findViewById(R.id.logo);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        answerATxt = findViewById(R.id.answerATxt);
        answerBTxt = findViewById(R.id.answerBTxt);
        answerCTxt = findViewById(R.id.answerCTxt);
        answerDTxt = findViewById(R.id.answerDTxt);
        questionBoardTxt = findViewById(R.id.questionBoardTxt);
        nextButton = findViewById(R.id.nextBtn);
        logoPath = getIntent().getIntExtra("logo", -1);
        if(logoPath!=-1){
            logo.setImageResource(logoPath);
        }
        questions = cQuest;
        loadQuestion();

        answerA.setOnClickListener(v -> checkAnswer("A"));
        answerB.setOnClickListener(v -> checkAnswer("B"));
        answerC.setOnClickListener(v -> checkAnswer("C"));
        answerD.setOnClickListener(v -> checkAnswer("D"));

        nextButton.setOnClickListener(v -> loadNextQuestion());
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionBoardTxt.setText(currentQuestion.getQuestionText());
            answerATxt.setText(currentQuestion.getAnswers().get(0));
            answerBTxt.setText(currentQuestion.getAnswers().get(1));
            answerCTxt.setText(currentQuestion.getAnswers().get(2));
            answerDTxt.setText(currentQuestion.getAnswers().get(3));

            resetAnswerColors();
        }
    }

    private void checkAnswer(String selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
            updateAnswerColor(selectedOption, true);
        } else {
            updateAnswerColor(selectedOption, false);
        }
    }

    private void updateAnswerColor(String selectedOption, boolean isCorrect) {
        CardView selectedCardView = null;

        switch (selectedOption) {
            case "A":
                selectedCardView = answerA;
                break;
            case "B":
                selectedCardView = answerB;
                break;
            case "C":
                selectedCardView = answerC;
                break;
            case "D":
                selectedCardView = answerD;
                break;
        }

        if (selectedCardView != null) {
            if (isCorrect) {
                selectedCardView.setCardBackgroundColor(getResources().getColor(R.color.main));
            } else {
                selectedCardView.setCardBackgroundColor(getResources().getColor(R.color.black));
            }
        }
    }

    private void resetAnswerColors() {
        answerA.setCardBackgroundColor(getResources().getColor(R.color.white));
        answerB.setCardBackgroundColor(getResources().getColor(R.color.white));
        answerC.setCardBackgroundColor(getResources().getColor(R.color.white));
        answerD.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            loadQuestion();
        } else {
            // Optionally, handle when there are no more questions

        }
    }
}
