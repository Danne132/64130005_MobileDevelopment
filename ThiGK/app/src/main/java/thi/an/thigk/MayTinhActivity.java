package thi.an.thigk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class MayTinhActivity extends AppCompatActivity {

    AppCompatButton btnCong, btnTru, btnNhan, btnChia;
    EditText txtSoA, txtSoB, txtKQ;
    List<AppCompatButton> btns;

    void getControl(){
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        btns = new ArrayList<>(Arrays.asList(btnCong, btnTru, btnNhan, btnChia));
        txtSoA = findViewById(R.id.txtSoA);
        txtSoB = findViewById(R.id.txtSoB);
        txtKQ = findViewById(R.id.txtKQ);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_may_tinh);
        getControl();
        for(AppCompatButton btn: btns){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check(btn);
                }
            });
        }

    }

    void check(AppCompatButton button){
        String phepTinh = button.getText().toString();
        Double soA = Double.NaN;
        Double soB = Double.NaN;
        Double kq = Double.NaN;
        if(txtSoA.getText().toString().isEmpty() || txtSoB.getText().toString().isEmpty()){
            txtKQ.setText(String.valueOf(kq));
        }
        else{
            soA = Double.parseDouble(txtSoA.getText().toString());
            soB = Double.parseDouble(txtSoB.getText().toString());
        }
        switch (phepTinh){
            case "+":{
                kq = soA + soB;
                break;
            }
            case "-":{
                kq = soA - soB;
                break;
            }
            case "x":{
                kq = soA * soB;
                break;
            }
            case "/":{
                if(soB == 0){
                    kq = Double.NaN;
                    break;
                }
                kq = soA/soB;
                break;
            }
            default:{
                kq = Double.NaN;
                break;
            }
        }
        txtKQ.setText(String.valueOf(kq));
    }

    public void viewTrangChu(View v){
        finish();
    }
}