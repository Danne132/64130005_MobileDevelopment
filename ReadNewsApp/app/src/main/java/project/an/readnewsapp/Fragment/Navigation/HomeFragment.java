package project.an.readnewsapp.Fragment.Navigation;

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
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import project.an.readnewsapp.Models.Categories;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;
import project.an.readnewsapp.Adapter.CategoryViewPageAdapter;


public class HomeFragment extends Fragment {

    TextInputLayout inputSearchLayout;
    TextInputEditText inputSearch;
    ImageView micro;
    TabLayout tabCategories;
    ViewPager2 viewNewsList;
    List<Categories> categoriesList;
    CategoryViewPageAdapter adapter;
    OkHttpClient client;

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
        categoriesList = new ArrayList<>(Arrays.asList(
                new Categories("AI/ML", "https://techcrunch.com/category/artificial-intelligence/feed/"),
                new Categories("Web Dev", "https://www.smashingmagazine.com/feed/"),
                new Categories("Technology", "https://www.cnet.com/rss/news/"),
                new Categories("Security", "https://techcrunch.com/category/security/feed/")
        ));
        adapter = new CategoryViewPageAdapter(this, categoriesList);
        viewNewsList.setAdapter(adapter);
    }

    private void setupView(){
        new TabLayoutMediator(tabCategories, viewNewsList, (tab, position) -> {
            View customTab = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null);
            TextView tabTitle = customTab.findViewById(R.id.tabTitle);
            tabTitle.setText(categoriesList.get(position).getTitle());
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