package th.hoangduyan.quizappgui;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    ImageView logo;
    int logoPath;

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
    }

}