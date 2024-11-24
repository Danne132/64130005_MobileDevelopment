package project.an.readnewsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class HomeFragment extends Fragment {

    TextInputLayout inputSearchLayout;
    TextInputEditText inputSearch;
    ImageView micro;
    TabLayout tabCategories;
    ViewPager2 viewNewsList;
    String[] tabTitles;
    ViewPageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getControl(view);
        setupView();
    }

    private void getControl(View view){
        inputSearchLayout = view.findViewById(R.id.inputSearchLayout);
        inputSearch = view.findViewById(R.id.inputSearch);
        micro = view.findViewById(R.id.micro);
        tabCategories = view.findViewById(R.id.tabCategories);
        viewNewsList = view.findViewById(R.id.viewNewsList);
        tabTitles = new String[]{"Random", "AI/ML", "Gaming", "Security", "VR/AR", "Software"};
        adapter = new ViewPageAdapter(this);
        viewNewsList.setAdapter(adapter);
    }

    private void setupView(){
        new TabLayoutMediator(tabCategories, viewNewsList, (tab, position) -> {
            View customTab = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null);
            TextView tabTitle = customTab.findViewById(R.id.tabTitle);
            tabTitle.setText(tabTitles[position]);
            tab.setCustomView(customTab);
        }).attach();
        tabCategories.addOnTabSelectedListener(tabSelectedListener);
    }

    TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            View customTab = tab.getCustomView();
            if (customTab != null){
                TextView tabTitle = customTab.findViewById(R.id.tabTitle);
                tabTitle.setTextColor(ContextCompat.getColor(requireContext(),R.color.white));
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            View customTab = tab.getCustomView();
            if (customTab != null){
                TextView tabTitle = customTab.findViewById(R.id.tabTitle);
                tabTitle.setTextColor(ContextCompat.getColor(requireContext(),R.color.content_color));
            }
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


}