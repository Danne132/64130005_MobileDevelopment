package project.an.readnewsapp.Service;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getContextForLanguage;
import static androidx.core.content.ContextCompat.getSystemService;

import static project.an.readnewsapp.RSSUtils.fetchRSS;
import static project.an.readnewsapp.RSSUtils.parseRSS;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import project.an.readnewsapp.Activity.MainActivity;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;
import project.an.readnewsapp.RSSUtils;

public class NewsCheckWorker extends Worker {
    private static final int NOTIFICATION_ID = 2;
    private List<String> rssUrls = Arrays.asList(
            "https://machinelearningmastery.com/blog/feed/",   // Link 1
            "https://dev.to/feed",   // Link 2
            "https://www.engadget.com/rss.xml",
            "https://hackernoon.com/feed"// Link 3
    );

    public NewsCheckWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        NewsItem newsItem = getLatestNews();
        sendNotification(newsItem.getTitle(), newsItem.getImgUrl());
        return Result.success();
    }

    private void sendNotification(String message, String imageURL) {
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.place_holder);
        if(imageURL != null)
            bitmap = getBitmapFromUrl(imageURL);
        // Tạo NotificationChannel (Android 8.0 trở lên)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    MainActivity.CHANNEL_ID,
                    "Periodic Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Tạo thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("New article")
                .setContentText(message)
                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Hiển thị thông báo
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private NewsItem getLatestNews() {
        try {
            for (String rssUrl : rssUrls) {
                String rssData = fetchRSS(rssUrl); // Gọi hàm lấy dữ liệu RSS đã có sẵn
                List<NewsItem> newsItems = parseRSS(rssData); // Parse RSS thành danh sách NewsItem

                for (NewsItem newsItem : newsItems) {
                    String today = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH).format(new Date());
                    if(newsItem.getPupDate().equals(today)){
                        String entryId = newsItem.getLink();
                        if (!isArticleNotified(entryId)) {
                            markArticleAsNotified(entryId); // Đánh dấu bài viết đã thông báo
                            return newsItem; // Trả về bài viết mới nhất
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Không có bài viết mới
    }

    private boolean isArticleNotified(String articleId) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("RSS_FEED_PREF", Context.MODE_PRIVATE);
        return preferences.getBoolean(articleId, false);
    }

    private void markArticleAsNotified(String articleId) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("RSS_FEED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(articleId, true);
        editor.apply();
    }

    private Bitmap getBitmapFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu không tải được ảnh
        }
    }
}
