package thi.an.thigk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

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

    void getControl(){
        bai1 = findViewById(R.id.bai1);
        bai2 = findViewById(R.id.bai2);
        bai3 = findViewById(R.id.bai3);
        bai4 = findViewById(R.id.bai4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();

    }

    public void viewTinhToan(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, MayTinhActivity.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }

    public void viewThongTin(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, MayTinhActivity.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }

    public void viewListView(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, MayTinhActivity.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }

    public void viewRecycleView(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, MayTinhActivity.class);
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }
}