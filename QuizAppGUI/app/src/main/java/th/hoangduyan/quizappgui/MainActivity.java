package th.hoangduyan.quizappgui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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
        cppChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("logo", R.drawable.cpp);
                startActivity(intent);
            }
        });
        javaChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("logo", R.drawable.java);
                startActivity(intent);
            }
        });
        pythonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("logo", R.drawable.python);
                startActivity(intent);
            }
        });
    }

}