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
    TextView questionBoard, answerATxt, answerBTxt, answerCTxt, answerDTxt;
    int logoPath;
    ArrayList<Question> questionsList;

    private void getControl(){
        logo = findViewById(R.id.logo);
        questionBoard = findViewById(R.id.questionBoard);
        answerATxt = findViewById(R.id.answerATxt);
        answerBTxt = findViewById(R.id.answerBTxt);
        answerCTxt = findViewById(R.id.answerCTxt);
        answerDTxt = findViewById(R.id.answerDTxt);
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
        subject = checkSubject(logoPath);

    }

    private String checkSubject(int logoPath){
        if (logoPath == R.drawable.cpp)
            return "C++";
        if (logoPath == R.drawable.java)
            return "Java";
        if (logoPath == R.drawable.python)
            return "Python";
        return null;
    }
    
}