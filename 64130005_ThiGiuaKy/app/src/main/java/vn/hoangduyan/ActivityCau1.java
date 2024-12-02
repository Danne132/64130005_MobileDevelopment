package vn.hoangduyan;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityCau1 extends AppCompatActivity {

    AppCompatButton btnCong, btnMain;
    EditText txtSoA, txtSoB, txtKQ;
    List<AppCompatButton> btns;
    double soA, soB, kq;
    void getControl(){
        btnCong = findViewById(R.id.btnCong);
        btnMain = findViewById(R.id.btnMain);
        btns = new ArrayList<>(Arrays.asList(btnCong, btnMain));
        txtSoA = findViewById(R.id.txtSoA);
        txtSoB = findViewById(R.id.txtSoB);
        txtKQ = findViewById(R.id.txtKQ);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        getControl();
        btnCong.setOnClickListener(clickCong);
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

    View.OnClickListener clickCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(check()) {
                soA = Double.parseDouble(txtSoA.getText().toString());
                soB = Double.parseDouble(txtSoB.getText().toString());
                kq = soA + soB;
                txtKQ.setText(String.valueOf(kq));
            }
            else {
                txtKQ.setText("NaN");
            }
        }
    };

    boolean check(){
        if(txtSoB.getText().toString().isEmpty() || txtSoA.getText().toString().isEmpty())
            return false;
        return true;
    }

    public void viewTrangChu(View v){
        finish();
    }
}