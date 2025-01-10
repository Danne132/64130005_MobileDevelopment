package project.an.readnewsapp.Service;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getContextForLanguage;
import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

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
import java.util.UUID;

import project.an.readnewsapp.Activity.MainActivity;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;
import project.an.readnewsapp.RSSUtils;

public class NewsCheckWorker extends Worker {
    private static final String CHANNEL_ID = "CHANNEL_1";
    private List<String> rssUrls = Arrays.asList(
            "https://machinelearningmastery.com/blog/feed/", 
            "https://dev.to/feed",
            "https://www.engadget.com/rss.xml",
            "https://hackernoon.com/feed"
    );

    public NewsCheckWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        createNotificationChannel(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Kiểm tra RSS feed tin tức mới
        NewsItem newsItem = checkForNewRSSFeed(); // Hàm kiểm tra RSS feed

        if (newsItem!=null) {
            sendNotification(newsItem.getTitle(), newsItem.getImgUrl());
        }

        return Result.success();
    }

    private NewsItem checkForNewRSSFeed() {
        try {
            for (String rssUrl : rssUrls) {
                String rssData = RSSUtils.fetchRSS(rssUrl);
                if(rssData.isEmpty()) continue;
                List<NewsItem> newsItems = RSSUtils.parseRSS(rssData);
                String today = new SimpleDateFormat("EEE, dd MMM yyyy").format(new Date());
                for(NewsItem newsItem : newsItems){
                    if(newsItem.getPupDate().equals(today)){
                        String entryId = newsItem.getLink();
                        if(!isArticleNotified(entryId)){
                            markArticleAsNotified(entryId);
                            return newsItem;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  // Không có tin mới
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

    private void sendNotification(String title, String imgURL) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap bitmap = null;
        if(imgURL != null){
            bitmap = getBitMapFromURL(imgURL);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Tin tức mới")
                .setContentText(title)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private Bitmap getBitMapFromURL(String imgURL) {
        try {
            URL url = new URL(imgURL);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu không tải được ảnh
        }
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            if(notificationManager != null)
                notificationManager.createNotificationChannel(channel);
        }
    }
}
