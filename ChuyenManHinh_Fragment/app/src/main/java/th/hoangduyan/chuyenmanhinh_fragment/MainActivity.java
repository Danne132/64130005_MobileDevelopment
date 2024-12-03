package th.hoangduyan.chuyenmanhinh_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button englishBtn, mathBtn, codeBtn;

    private void getControl(){
        englishBtn = findViewById(R.id.englishBtn);
        mathBtn = findViewById(R.id.mathBtn);
        codeBtn = findViewById(R.id.codeBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();
        englishBtn.setOnClickListener(englishClick);
        mathBtn.setOnClickListener(mathClick);
        codeBtn.setOnClickListener(codeClick);
    }

    View.OnClickListener englishClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, EnglishActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener mathClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MathActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener codeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CodeActivity.class);
            startActivity(intent);
        }
    };
}