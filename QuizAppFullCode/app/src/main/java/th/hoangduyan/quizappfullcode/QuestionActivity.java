package th.hoangduyan.quizappfullcode;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    String subject;
    ImageView logo;
    TextView questionBoard, answerATxt, answerBtxt, answerCTxt, answerDtxt;
    int logoPath;
    ArrayList<Question> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        logo = findViewById(R.id.logo);
        logoPath = getIntent().getIntExtra("logo", -1);
        if(logoPath!=-1){
            logo.setImageResource(logoPath);
        }
        if (logoPath == R.drawable.cpp) {
            subject = "C++";
        } else if (logoPath == R.drawable.java) {
            subject = "Java";
        } else if (logoPath == R.drawable.python) {
            subject = "Python";
        }

        questionsList = loadQuestionsFromJSON(this, subject);



    }

    private ArrayList<Question> loadQuestionsFromJSON(Context context, String subject) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("questions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonStr = new String(buffer, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonStr);

            // Lấy danh sách câu hỏi theo chủ đề
            JSONArray questionsArray = jsonObject.getJSONObject("questions").getJSONArray(subject);
            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionObj = questionsArray.getJSONObject(i);

                String questionText = questionObj.getString("question_text");
                String correctAnswer = questionObj.getString("correct_answer");
                JSONArray wrongAnswersArray = questionObj.getJSONArray("wrong_answers");

                ArrayList<String> wrongAnswers = new ArrayList<>();
                for (int j = 0; j < wrongAnswersArray.length(); j++) {
                    wrongAnswers.add(wrongAnswersArray.getString(j));
                }

                questions.add(new Question(questionText, correctAnswer, wrongAnswers));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
}