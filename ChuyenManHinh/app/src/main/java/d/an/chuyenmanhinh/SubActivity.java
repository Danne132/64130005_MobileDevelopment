package d.an.chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        //Lấy intent về

    }

    //Xử lý onClick của button để chuyển qua màn hình khác
    public void veTrangChu(View v){
        Intent iNhanIntent = getIntent();
        //Lấy dữ liệu được đưa qua từ màn hình trước đó
        String data = iNhanIntent.getStringExtra("name");
        //Hiện dữ liệu
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}