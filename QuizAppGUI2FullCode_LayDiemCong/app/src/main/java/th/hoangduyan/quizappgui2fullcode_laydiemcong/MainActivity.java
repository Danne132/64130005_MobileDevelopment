package th.hoangduyan.quizappgui2fullcode_laydiemcong;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageView intro = findViewById(R.id.intro);
        Glide.with(this)
                .asGif()
                .load(R.drawable.congrat)
                .into(intro);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onTouchEvent(event);
    }
}