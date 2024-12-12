package th.hoangduyan.quizappgui2fullcode_laydiemcong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView quesTxt, answerATxt, answerBTxt, answerCTxt, answerDTxt, resultTxt;
    private ImageView quesImg;
    private CardView answerALayout, answerBLayout, answerCLayout, answerDLayout;
    private Button nextBtn;
    private List<Question> questionList;
    private List<TextView> answerList;
    private List<CardView> answerLayoutList;
    private int currentQuestion;

    private void getControl(){
        quesTxt = findViewById(R.id.quesTxt);
        answerATxt = findViewById(R.id.answerATxt);
        answerBTxt = findViewById(R.id.answerBTxt);
        answerCTxt = findViewById(R.id.answerCTxt);
        answerDTxt = findViewById(R.id.answerDTxt);
        quesImg = findViewById(R.id.quesImg);
        answerALayout = findViewById(R.id.answerALayout);
        answerBLayout = findViewById(R.id.answerBLayout);
        answerCLayout = findViewById(R.id.answerCLayout);
        answerDLayout = findViewById(R.id.answerDLayout);
        nextBtn = findViewById(R.id.nextBtn);
        answerList = new ArrayList<>(Arrays.asList(answerATxt, answerBTxt, answerCTxt, answerDTxt));
        answerLayoutList = new ArrayList<>(Arrays.asList(answerALayout, answerBLayout, answerCLayout, answerDLayout));
        createQuestion();
        currentQuestion = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        getControl();
        nextBtn.setOnClickListener(nextQues);
        displayQuestion(currentQuestion);
    }

    View.OnClickListener nextQues = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            currentQuestion++;
            displayQuestion(currentQuestion);
        }
    };

    private void displayQuestion(int currentQuestion){
        Question ques = questionList.get(currentQuestion);
        quesTxt.setText(ques.getQuestion());
        if(questionList.get(currentQuestion).getImage() != -1){
            quesImg.setVisibility(View.VISIBLE);
            quesImg.setImageResource(ques.getImage());
        }

        for(int i = 0; i < answerList.size(); i++){
            answerList.get(i).setText(ques.getAnswers().get(i));
        }
    }

    private void createQuestion(){
        questionList = new ArrayList<>(Arrays.asList(
                new Question(
                        "What is the capital of France?",
                        "Paris",
                        Arrays.asList("Paris", "London", "Berlin", "Madrid"),
                        -1
                ),
                new Question(
                        "Who invented Java programming language?",
                        "James Gosling",
                        Arrays.asList("James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum"),
                        -1
                ),
                new Question(
                        "What is the square root of 64?",
                        "8",
                        Arrays.asList("6", "7", "8", "9"),
                        -1
                ),
                new Question(
                        "Which planet is known as the Red Planet?",
                        "Mars",
                        Arrays.asList("Earth", "Venus", "Mars", "Jupiter"),
                        -1
                ),
                new Question(
                        "Which language is used for Android development?",
                        "Java",
                        Arrays.asList("C++", "Java", "Python", "Ruby"),
                        -1
                ),
                new Question(
                        "What is the boiling point of water in Celsius?",
                        "100",
                        Arrays.asList("90", "100", "110", "120"),
                        -1
                ),
                new Question(
                        "What is the name of the largest ocean on Earth?",
                        "Pacific Ocean",
                        Arrays.asList("Atlantic Ocean", "Pacific Ocean", "Indian Ocean", "Arctic Ocean"),
                        -1
                ),
                new Question(
                        "Which year did World War II end?",
                        "1945",
                        Arrays.asList("1939", "1941", "1945", "1950"),
                        -1
                ),
                new Question(
                        "Which gas do plants absorb during photosynthesis?",
                        "Carbon dioxide",
                        Arrays.asList("Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen"),
                        -1
                ),
                new Question(
                        "What is the largest animal on Earth?",
                        "Blue Whale",
                        Arrays.asList("Elephant", "Blue Whale", "Great White Shark", "Giraffe"),
                        -1
                )
        ));
    }
}