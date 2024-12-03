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
                new Categories("VR/AR", "https://techcrunch.com/category/vr-ar/feed/"),
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

    public static String fetchRSS(String newsURL) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(newsURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Thời gian chờ kết nối
            connection.setReadTimeout(5000); // Thời gian chờ đọc dữ liệu

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static List<NewsItem> parseRSS(String rssData) {
        List<NewsItem> newsItems = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new java.io.ByteArrayInputStream(rssData.getBytes()));

            NodeList itemNodes = document.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Node itemNode = itemNodes.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;

                    // Trích xuất các trường dữ liệu
                    String title = getElementValue(itemElement, "title");
                    String link = getElementValue(itemElement, "link");
                    String description = getElementValue(itemElement, "description");
                    String pubDate = getElementValue(itemElement, "pubDate");

                    // Giả sử hình ảnh nằm trong thẻ <media:content> hoặc trong <description>
                    String imageUrl = getElementValue(itemElement, "media:thumbnail");
                    if (imageUrl == null || imageUrl.isEmpty()) {
                        imageUrl = extractImageFromDescription(description);
                    }

//                  RSSItem rssItem = new RSSItem(title, link, description, pubDate, imageUrl);
                    NewsItem newsItem = new NewsItem(title, imageUrl, description, pubDate, link);
                    newsItems.add(newsItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsItems;
    }
    // Trích xuất nội dung từ thẻ XML
    private static String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }

    // Lấy URL hình ảnh từ nội dung <description> (nếu cần)
    private static String extractImageFromDescription(String description) {
        if (description == null) return null;
        int imgStart = description.indexOf("<img");
        if (imgStart != -1) {
            int srcStart = description.indexOf("src=\"", imgStart);
            int srcEnd = description.indexOf("\"", srcStart + 5);
            if (srcStart != -1 && srcEnd != -1) {
                return description.substring(srcStart + 5, srcEnd);
            }
        }
        return null;
    }
}