package project.an.rssapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // Danh sách các chủ đề
        List<RSSCategory> categories = new ArrayList<>();
        categories.add(new RSSCategory("Technology", "https://dev.to/feed"));
        categories.add(new RSSCategory("Web Dev", "https://www.smashingmagazine.com/feed/"));
        categories.add(new RSSCategory("Technology", "https://www.wired.com/feed/category/security/latest/rss"));


        // Gắn adapter cho ViewPager
        RSSPagerAdapter pagerAdapter = new RSSPagerAdapter(this, categories);
        viewPager.setAdapter(pagerAdapter);

        // Đồng bộ TabLayout với ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(categories.get(position).getTitle());
        }).attach();

    }

    public static String fetchRSS(String rssUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(rssUrl);
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

    public static List<RSSItem> parseRSS(String rssData) {
        List<RSSItem> rssItems = new ArrayList<>();
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
                    RSSItem rssItem = new RSSItem(title, imageUrl, description, pubDate, link);
                    rssItems.add(rssItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rssItems;
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