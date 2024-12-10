package th.hoangduyan.quizappfullcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    LinearLayout cppChoose, javaChoose, pythonChoose;

    public void getControl(){
        cppChoose = findViewById(R.id.cppChoose);
        javaChoose = findViewById(R.id.javaChoose);
        pythonChoose = findViewById(R.id.pythonChoose);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();
        cppChoose.setOnClickListener(cppClick);
        javaChoose.setOnClickListener(javaClick);
        pythonChoose.setOnClickListener(pythonClick);
    }

    View.OnClickListener cppClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            intent.putExtra("logo", R.drawable.cpp);
            startActivity(intent);
        }
    };

    View.OnClickListener javaClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            intent.putExtra("logo", R.drawable.java);
            startActivity(intent);
        }
    };

    View.OnClickListener pythonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            intent.putExtra("logo", R.drawable.python);
            startActivity(intent);
        }
    };
}