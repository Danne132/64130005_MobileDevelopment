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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import project.an.readnewsapp.Activity.MainActivity;
import project.an.readnewsapp.R;

public class NewsCheckWorker extends Worker {
    private static final String CHANNEL_ID = "CHANNEL_1";
    private List<String> rssUrls = Arrays.asList(
            "https://machinelearningmastery.com/blog/feed/",   // Link 1
            "https://dev.to/feed",   // Link 2
            "https://www.engadget.com/rss.xml",
            "https://hackernoon.com/feed"// Link 3
    );

    public NewsCheckWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        createNotificationChannel(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Kiểm tra RSS feed tin tức mới
        boolean hasNewNews = checkForNewRSSFeed(); // Hàm kiểm tra RSS feed

        if (hasNewNews) {
            sendNotification("Tin tức mới", "Xem ngay bài báo mới!");
        }

        return Result.success();
    }

    private boolean checkForNewRSSFeed() {
        try {
            for (String rssUrl : rssUrls) {
                URL url = new URL(rssUrl);
                InputStream inputStream = url.openStream();
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build((Document) inputStream);

                List<SyndEntry> entries = feed.getEntries();
                if (!entries.isEmpty()) {
                    SyndEntry latestEntry = entries.get(0);
                    String title = latestEntry.getTitle();

                    // Lấy ID bài viết (hoặc có thể lấy ngày đăng)
                    String entryId = latestEntry.getUri(); // Hoặc dùng ID, URL, ngày đăng

                    // Kiểm tra bài viết đã thông báo chưa (so sánh với thông tin đã lưu)
                    if (!isArticleNotified(entryId)) {
                        // Lưu ID bài viết vào SharedPreferences
                        markArticleAsNotified(entryId);
                        return true;  // Nếu chưa thông báo về bài viết này
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;  // Không có tin mới
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

    private void sendNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        notificationManager.notify(1, builder.build());
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
