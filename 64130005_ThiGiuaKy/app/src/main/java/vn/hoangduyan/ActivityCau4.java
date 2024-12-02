package vn.hoangduyan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityCau4 extends AppCompatActivity {

    Button btnGithub, btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau4);
        btnGithub = findViewById(R.id.btnGithub);
        btnMain = findViewById(R.id.btnMain);
        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    String url = "https://github.com/Danne132";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btnMain.setOnTouchListener(touchBtn);
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

    public void viewTrangChu(View v){
        finish();
    }
}