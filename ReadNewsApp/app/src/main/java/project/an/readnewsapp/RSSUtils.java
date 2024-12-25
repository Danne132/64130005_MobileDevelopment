package project.an.readnewsapp;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import project.an.readnewsapp.Models.NewsItem;

public class RSSUtils {

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
                    String imageUrl = extractImageFromMediaContent(itemElement, "media:content");
                    if (imageUrl == null || imageUrl.isEmpty()) {
//                        imageUrl = extractImageFromDescription(description);
                        imageUrl = extractImageFromMediaContent(itemElement, "enclosure");
                    }
                    if(imageUrl!=null) Log.i("Main", "Get image success");
                    else Log.i("Main", "Failed to get image");
                    NewsItem newsItem = new NewsItem(title, imageUrl, pubDate, link);
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

    private static String extractImageFromMediaContent(Element parent, String tagName) {
        NodeList mediaContentList = parent.getElementsByTagName(tagName);
        for (int i = 0; i < mediaContentList.getLength(); i++) {
            Node node = mediaContentList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element mediaElement = (Element) node;
                if (mediaElement.hasAttribute("url")) {
                    return mediaElement.getAttribute("url");
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy thẻ hoặc thuộc tính "url"
    }


}
