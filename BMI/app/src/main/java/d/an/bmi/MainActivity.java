package d.an.bmi;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.function.DoubleUnaryOperator;

public class MainActivity extends AppCompatActivity {

    EditText txtH, txtW;
    Button btnTinh;
    TextView textViewKetQua, textViewNhanXet;

    void getControl(){
        txtH = findViewById(R.id.txtH);
        txtW = findViewById(R.id.txtW);
        btnTinh = findViewById(R.id.btnTinh);
        textViewKetQua = findViewById(R.id.textViewKetQua);
        textViewNhanXet = findViewById(R.id.textViewNhanXet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControl();
        btnTinh.setOnClickListener(tinhBMI);
    }

    View.OnClickListener tinhBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textViewKetQua.setText(String.valueOf(TinhBMI()));
            if(TinhBMI() < 18.5){
                textViewNhanXet.setText("Ốm");
            } else if (TinhBMI() < 24.9) {
                textViewNhanXet.setText("Bình thường");
            } else if (TinhBMI() < 39.9) {
                textViewNhanXet.setText("Béo phì");
            }
        }
    };

    double TinhBMI(){
        double height = Double.parseDouble(txtH.getText().toString());
        double weight = Double.parseDouble(txtW.getText().toString());
        double BMI = weight/(height*height);
        return Math.round(BMI*100.0)/100.0;
    }
}