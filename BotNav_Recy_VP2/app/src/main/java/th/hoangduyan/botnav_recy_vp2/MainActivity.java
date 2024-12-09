package th.hoangduyan.botnav_recy_vp2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CN1Fragment()).commit();
        }
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if(item.getItemId()==R.id.cn1){
                selectedFragment = new CN1Fragment();
            } else if (item.getItemId()==R.id.cn2) {
                selectedFragment = new CN2Fragment();
            }else if (item.getItemId()==R.id.cn3) {
                selectedFragment = new CN3Fragment();
            }else {
                selectedFragment = new CN4Fragment();

            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            }
            return true;
        });
    }
}