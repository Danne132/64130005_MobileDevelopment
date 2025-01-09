package project.an.readnewsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import project.an.readnewsapp.R;
import project.an.readnewsapp.Service.DatabaseHelper;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView imageDetailNews, backToHome, bookmarkDetail, shareNews, readAloud;
    private TextView titleDetailTxt, categoryDetail, contentDetail, pubDateDetail;
    private DatabaseHelper databaseHelper;
    private TextToSpeech tts;
    private String link, title, imageUrl, content, pubDate, category;
    private NestedScrollView nestedScrollDetails;
    private ScrollView scrollViewDetail;

    private void getControl(){
        imageDetailNews = findViewById(R.id.imageDetailNews);
        backToHome = findViewById(R.id.backToHome);
        titleDetailTxt = findViewById(R.id.titleDetailTxt);
        categoryDetail = findViewById(R.id.categoryDetail);
        contentDetail = findViewById(R.id.contentDetail);
        pubDateDetail = findViewById(R.id.pubDateDetail);
        bookmarkDetail = findViewById(R.id.bookmarkDetail);
        shareNews = findViewById(R.id.shareNews);
        readAloud = findViewById(R.id.readAloud);
        nestedScrollDetails = findViewById(R.id.nestedScrollDetails);
        scrollViewDetail = findViewById(R.id.scrollViewDetail);
        databaseHelper = DatabaseHelper.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getControl();
        getNewsIntent();
        setUp();
        shareNews.setOnClickListener(shareClick);
        bookmarkDetail.setOnClickListener(bookmarkClick);
        tts = readAloud();
        readAloud.setOnClickListener(readContent);
    }

    private void getNewsIntent(){
        title = getIntent().getStringExtra("title");
        imageUrl = getIntent().getStringExtra("imageUrl");
        content = getIntent().getStringExtra("content");
        pubDate = getIntent().getStringExtra("pubDate");
        category = getIntent().getStringExtra("category");
        link = getIntent().getStringExtra("link");
    }

    private void setUp(){
        if(databaseHelper.isBookmarked(title)){
            bookmarkDetail.setImageResource(R.drawable.icon_bookmark_chosen);
        }
        else bookmarkDetail.setImageResource(R.drawable.icon_bookmark_detail);
        categoryDetail.setText(category);
        titleDetailTxt.setText(title);
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.place_holder) // Hình ảnh thay thế khi đang tải
                .into(imageDetailNews);
        pubDateDetail.setText(pubDate);
//        Chuyển hóa các thẻ HTML
        Spanned plainText = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);
        contentDetail.setText(plainText);
        backToHome.setOnClickListener(backClick);
        /*nestedScrollDetails.setOnScrollChangeListener(scrollParent);*/
    }

    View.OnClickListener bookmarkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isBookmarkedcheck = databaseHelper.isBookmarked(title);
            if (isBookmarkedcheck) {
                int rowsDeleted = databaseHelper.deleteBookmark(title);
                if (rowsDeleted > 0) {

                    Toast.makeText(getApplicationContext(), "Đã xóa khỏi bookmark", Toast.LENGTH_SHORT).show();
                    bookmarkDetail.setImageResource(R.drawable.icon_bookmark_detail);
                } else {
                    Toast.makeText(getApplicationContext(), "Lỗi khi xóa bookmark", Toast.LENGTH_SHORT).show();
                }
            } else {
                long result = databaseHelper.insertBookmark(
                        title,
                        imageUrl,
                        link,
                        content,
                        pubDate,
                        category
                );
                if (result > 0) {
                    Toast.makeText(getApplicationContext(), "Đã thêm vào bookmark", Toast.LENGTH_SHORT).show();
                    bookmarkDetail.setImageResource(R.drawable.icon_bookmark_chosen);
                    Toast.makeText(getApplicationContext(), "Lỗi khi thêm bookmark", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    View.OnClickListener shareClick = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            // Tạo Intent chia sẻ
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, link);
            startActivity(Intent.createChooser(shareIntent, "Chia sẻ đường link qua:"));
        }
    };

    View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /*View.OnScrollChangeListener scrollParent = new View.OnScrollChangeListener() {
        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            Log.i("ScrollY", "Current scrollY:"+scrollY);
            Log.i("imagewidth", ""+params.width);
            Log.i("imageheight", ""+params.height);
            // Nếu cuộn ngoài đạt đến vị trí ngưỡng
            if (scrollY >= 300) {
                Log.i("ScrollY", "Đã quá 300");
                imageDetailNews.setLayoutParams(params);
                nestedScrollDetails.setEnabled(false);
                scrollViewDetail.setEnabled(true);
            } else {
                // Khi cuộn trở lại phần đầu, bật lại cuộn ngoài
                nestedScrollDetails.setEnabled(true);
                scrollViewDetail.setEnabled(false); // Tắt cuộn bên trong
            }

        }
    };*/

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private TextToSpeech readAloud(){
        TextToSpeech toSpeech =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Set language
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle the error
                        readAloud.setImageResource(R.drawable.icon_read);
                    } else {
                        readAloud.setImageResource(R.drawable.icon_read_choose);
                    }
                }
            }
        });
        return toSpeech;
    }

    View.OnClickListener readContent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tts.speak(content, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    };

}