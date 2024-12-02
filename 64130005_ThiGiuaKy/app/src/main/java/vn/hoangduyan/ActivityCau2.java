package vn.hoangduyan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityCau2 extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    ListView listViewCLC2;
    EditText editTextFind;
    Button btnFind;

    void getControl() {
        listViewCLC2 = findViewById(R.id.listViewCLC2);
        editTextFind = findViewById(R.id.editTextFind);
        btnFind = findViewById(R.id.btnFind);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        getControl();
        addStudent();
        ActivityCau2_Sub adapter = new ActivityCau2_Sub(this, list);
        listViewCLC2.setAdapter(adapter);
        listViewCLC2.setOnItemClickListener(clickListener);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findName();
            }
        });
    }

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ActivityCau2.this, ItemActivity.class);
            intent.putExtra("name", list.get(position));
            startActivity(intent);
        }
    };

    void addStudent() {
        list.add("Hoàng Duy An");
        list.add("Hoàng Duy An");
        list.add("Trần Doãn Anh");
        list.add("Trần Quốc Bảo");
        list.add("Dương Thị Ánh Sen");
        list.add("Hà Thị Kiều Ngân");
        list.add("Ngô Việt Hoàng");
        list.add("Hoàng Duy An");
        list.add("Hoàng Duy An");
        list.add("Trần Doãn Anh");
        list.add("Trần Quốc Bảo");
        list.add("Dương Thị Ánh Sen");
        list.add("Hà Thị Kiều Ngân");
        list.add("Ngô Việt Hoàng");
    }

    void findName(){
        ArrayList<String> namefilter = new ArrayList<>(list.stream()
                .filter(l -> l.contains(editTextFind.getText().toString()))
                .collect(Collectors.toList()));
        ActivityCau2_Sub adapter = new ActivityCau2_Sub(this, namefilter);
        listViewCLC2.setAdapter(adapter);
    }
    public void viewTrangChu(View v){
        finish();
    }
}