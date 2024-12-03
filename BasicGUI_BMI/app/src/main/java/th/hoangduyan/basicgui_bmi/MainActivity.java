package th.hoangduyan.basicgui_bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtHeight, txtWeight, txtKQ, txtDG;
    CheckBox cbIsAsian;
    Button tinhBtn;

    private void getControl(){
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        txtKQ = findViewById(R.id.txtKQ);
        txtDG = findViewById(R.id.txtDG);
        cbIsAsian = findViewById(R.id.cbIsAsian);
        tinhBtn = findViewById(R.id.tinhBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();
        tinhBtn.setOnClickListener(btnClick);
    }

    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tinhBMI();
        }
    };

    void tinhBMI(){
        double height = Double.parseDouble(txtHeight.getText().toString());
        double weight = Double.parseDouble(txtWeight.getText().toString());
        double bmi;
        boolean isAsian;
        if(height < 0 || weight < 0){
            Toast.makeText(this, "Chiều cao và cân nặng cần lớn hơn 0", Toast.LENGTH_LONG).show();
            return;
        }
        bmi = weight / (height * height);
        txtKQ.setText(String.valueOf(bmi));
        isAsian = cbIsAsian.isChecked();
        if (isAsian) {
            if(bmi < 17.5) txtDG.setText("Underweight");
            else if (bmi < 22.99) txtDG.setText("Normal");
            else if (bmi < 27.99) txtDG.setText("Over weight");
            else txtDG.setText("Obese");
        } else {
            if(bmi < 18.5) txtDG.setText("Underweight");
            else if (bmi < 24.99) txtDG.setText("Normal");
            else if (bmi < 29.99) txtDG.setText("Over weight");
            else txtDG.setText("Obese");
        }
    }
}