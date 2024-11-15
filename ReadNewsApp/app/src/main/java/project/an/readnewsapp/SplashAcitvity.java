package project.an.readnewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_acitvity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Chuyển sang MainActivity (hoặc Activity kế tiếp)
                Intent intent = new Intent(SplashAcitvity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Đóng SplashActivity sau khi chuyển đến MainActivity
            }
        }, 2000);
    }
}