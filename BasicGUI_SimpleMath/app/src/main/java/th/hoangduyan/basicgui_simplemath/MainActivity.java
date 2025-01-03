package th.hoangduyan.basicgui_simplemath;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

    RadioGroup radioGroup;
    Button btnTinh;
    EditText txtSoA, txtSoB, txtKQ;
    List<AppCompatButton> btns;
    RadioButton radioCong, radioTru, radioNhan, radioChia;
    double soA, soB, kq;
    void getControl(){
        btnTinh = findViewById(R.id.tinhBtn);
        radioGroup = findViewById(R.id.radioGroup);
        txtSoA = findViewById(R.id.txtSoA);
        txtSoB = findViewById(R.id.txtSoB);
        txtKQ = findViewById(R.id.txtKQ);
        radioCong = findViewById(R.id.radioCong);
        radioTru = findViewById(R.id.radioTru);
        radioNhan = findViewById(R.id.radioNhan);
        radioChia = findViewById(R.id.radioChia);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateM();
            }
        });
    }


    private void calculateM() {
        String number1Str = txtSoA.getText().toString();
        String number2Str = txtSoB.getText().toString();

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            Toast.makeText(MainActivity.this, "Vui lòng nhập đủ số", Toast.LENGTH_SHORT).show();
            return;
        }

        double number1 = Double.parseDouble(number1Str);
        double number2 = Double.parseDouble(number2Str);
        double result = 0;
        boolean isOperationSelected = false;

        if (radioCong.isChecked()) {
            result = number1 + number2;
            isOperationSelected = true;
        } else if (radioTru.isChecked()) {
            result = number1 - number2;
            isOperationSelected = true;
        } else if (radioNhan.isChecked()) {
            result = number1 * number2;
            isOperationSelected = true;
        } else if (radioChia.isChecked()) {
            if (number2 == 0) {
                Toast.makeText(MainActivity.this, "Số thứ hai phải khác 0", Toast.LENGTH_SHORT).show();
                return;
            }
            result = number1 / number2;
            isOperationSelected = true;
        }

        if (isOperationSelected) {
            txtKQ.setText(String.valueOf(result));
        } else {
            Toast.makeText(MainActivity.this, "Hãy chọn phép toán", Toast.LENGTH_SHORT).show();
        }
    }
}