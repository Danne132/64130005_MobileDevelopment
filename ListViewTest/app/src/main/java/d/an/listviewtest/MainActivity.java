package d.an.listviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    ListView listViewCLC2;
    EditText editTextFind;
    Button btnFind;

    void getControl(){
        listViewCLC2 = findViewById(R.id.listViewCLC2);
        editTextFind = findViewById(R.id.editTextFind);
        btnFind = findViewById(R.id.btnFind);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControl();
        addStudent();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listViewCLC2.setAdapter(adapter);
        listViewCLC2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findName();
            }
        });
    }

    void addStudent(){
        list.add("Hoàng Duy An");
        list.add("Trần Doãn Anh");
        list.add("Trần Quốc Bảo");
        list.add("Dương Thị Ánh Sen");
        list.add("Hà Thị Kiều Ngân");
        list.add("Ngô Việt Hoàng");
    }

    void findName(){
        List<String> namefilter = list.stream().filter(l -> l.contains(String.valueOf(editTextFind.getText()))).collect(Collectors.toList());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namefilter);
        listViewCLC2.setAdapter(adapter);
    }
}