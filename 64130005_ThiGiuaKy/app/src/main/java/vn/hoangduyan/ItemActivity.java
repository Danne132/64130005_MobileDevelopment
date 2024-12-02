package vn.hoangduyan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItemActivity extends AppCompatActivity {


    ImageView imgBg;
    TextView txtName, txtPop;
    String name, pop;
    int urlImage;

    void getControl(){
        imgBg = findViewById(R.id.imgBg);
        txtName = findViewById(R.id.txtName);
        txtPop = findViewById(R.id.txtPop);
        Intent nhanI = getIntent();
        name = nhanI.getStringExtra("name");
        pop = nhanI.getStringExtra("pop");
        urlImage = nhanI.getIntExtra("url", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
        getControl();
        txtName.setText(name);
        txtPop.setText("(" + pop + ")");
        if(urlImage == 0)
            imgBg.setImageResource(R.drawable.bg);
        else
            imgBg.setImageResource(urlImage);
    }
    public void viewTrangChu(View v){
        finish();
    }

}