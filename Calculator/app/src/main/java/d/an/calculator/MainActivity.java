package d.an.calculator;

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

public class MainActivity extends AppCompatActivity {
    //Khai báo các điều khiển
    Button btnCong, btnTru, btnNhan, btnChia;
    EditText txtA, txtB;
    TextView textViewKetQua;

    //Hàm tìm các điều khiển, cất vào biến ở trên
    void getControls(){
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnChia = findViewById(R.id.btnChia);
        btnNhan = findViewById(R.id.btnNhan);
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hàm này được gọi "đầu tiên" trước khi App hiện ra
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// Gắn với giao diện
        //3.Gọi hàm
        getControls();


        //B2 của nghe và xử lý sự kiện:
        btnTru.setOnClickListener(langNgheTru);

        //Có thể dùng cách 2 là tạo lắng nghe và sự kiện ẩn danh
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a=  Double.parseDouble( txtA.getText().toString()  )  ;
                double b=  Double.parseDouble( txtB.getText().toString()  )  ;

                double kq = a*b;

                textViewKetQua.setText(  String.valueOf(kq) );
            }
        });
    }

    //Tạo chức năng nghe và xử lý sự kiện
    //B1: tạo bộ (biến) lắng nghe và đáp ứng lại sự kiện Use Click lên nút trừ
    View.OnClickListener langNgheTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //B3: Lập trình
            double a=  Double.parseDouble( txtA.getText().toString()  )  ;
            double b=  Double.parseDouble( txtB.getText().toString()  )  ;

            double kq = a-b;

            textViewKetQua.setText(  String.valueOf(kq) );
        }
    };

    // Xu ly sư kiện
    public void XuLy_Cong(View v) {
        //Vì đã tìm đk rôồi, nên ta chỉ sử dụng thôi
        double a=  Double.parseDouble( txtA.getText().toString()  )  ;
        double b=  Double.parseDouble( txtB.getText().toString()  )  ;

        double kq = a+b;

        textViewKetQua.setText(  String.valueOf(kq) );

    }
    public void XuLy_Tru(View v) {
        //Vì đã tìm đk rôồi, nên ta chỉ sử dụng thôi
    }
    public void XuLy_Chia(View v) {
        //Vì đã tìm đk rôồi, nên ta chỉ sử dụng thôi
        double a=  Double.parseDouble( txtA.getText().toString()  )  ;
        double b=  Double.parseDouble( txtB.getText().toString()  )  ;

        double kq = a*b;

        textViewKetQua.setText(  String.valueOf(kq) );

    }
    public void XuLy_Nhan(View v) {


    }

}