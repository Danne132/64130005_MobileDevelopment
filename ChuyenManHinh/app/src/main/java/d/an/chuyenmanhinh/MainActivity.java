package d.an.chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    //Xử lý onClick của button để chuyển qua màn hình khác
    public void quaTrangKhac(View v){
        //Tạo một đối tượng Intent
        Intent iManHinhKhac = new Intent(MainActivity.this, SubActivity.class);
        //Gọi dữ liệu, dòng này ở bài này thì chưa cần
        iManHinhKhac.putExtra("name", "Hoàng Duy An");
        //Chuyển màn hình
        startActivity(iManHinhKhac);
    }
}