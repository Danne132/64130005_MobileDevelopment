package project.an.readnewsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    ImageView homeNav, categoriesNav, bookmarkNav, profileNav;

    private void getControl(){
        homeNav = findViewById(R.id.homeNav);
        categoriesNav = findViewById(R.id.categoriesNav);
        bookmarkNav = findViewById(R.id.bookmarkNav);
        profileNav = findViewById(R.id.profileNav);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getControl();
        replaceFragment(new HomeFragment());
        homeNav.setOnClickListener(homeClick);
        categoriesNav.setOnClickListener(categoriesClick);
        bookmarkNav.setOnClickListener(bookmarkClick);
        profileNav.setOnClickListener(profileClick);
    }

    View.OnClickListener homeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            replaceFragment(new HomeFragment());
        }
    };
    View.OnClickListener categoriesClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            replaceFragment(new CategoriesFragment());
        }
    };
    View.OnClickListener bookmarkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            replaceFragment(new BookmarkFragment());
        }
    };
    View.OnClickListener profileClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            replaceFragment(new ProfileFragment());
        }
    };

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}