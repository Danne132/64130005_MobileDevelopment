package vn.hoangduyan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppCompatButton bai1, bai2, bai3, bai4;
    List<AppCompatButton> btns;

    void getControl() {
        bai1 = findViewById(R.id.bai1);
        bai2 = findViewById(R.id.bai2);
        bai3 = findViewById(R.id.bai3);
        bai4 = findViewById(R.id.bai4);
        btns = new ArrayList<>(Arrays.asList(bai1, bai2, bai3, bai4));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();
        for(AppCompatButton btn : btns){
            btn.setOnTouchListener(touchBtn);
        }
    }

    View.OnTouchListener touchBtn = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.setScaleX(0.9f);
                v.setScaleY(0.9f);
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                v.setScaleX(1.0f);
                v.setScaleY(1.0f);
            }
            return false;
        }
    };

    public void viewTinhToan(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, ActivityCau1.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }

    public void viewThongTin(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, ActivityCau2.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }

    public void viewListView(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, ActivityCau3.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }

    public void viewRecycleView(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, ActivityCau4.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }
}