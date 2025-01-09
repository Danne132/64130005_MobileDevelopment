package project.an.readnewsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.Locale;

import project.an.readnewsapp.R;
import project.an.readnewsapp.Service.DatabaseHelper;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView imageDetailNews, backToHome, bookmarkDetail, shareNews;
    private TextView titleDetailTxt, categoryDetail, contentDetail, pubDateDetail;
    private DatabaseHelper databaseHelper;
    private TextToSpeech tts;
    private String link, title, imageUrl, content, pubDate, category;

    private void getControl(){
        imageDetailNews = findViewById(R.id.imageDetailNews);
        backToHome = findViewById(R.id.backToHome);
        titleDetailTxt = findViewById(R.id.titleDetailTxt);
        categoryDetail = findViewById(R.id.categoryDetail);
        contentDetail = findViewById(R.id.contentDetail);
        pubDateDetail = findViewById(R.id.pubDateDetail);
        bookmarkDetail = findViewById(R.id.bookmarkDetail);
        shareNews = findViewById(R.id.shareNews);
        databaseHelper = DatabaseHelper.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getControl();
        getNewsIntent();
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
        shareNews.setOnClickListener(shareClick);
    }

    private void getNewsIntent(){
        title = getIntent().getStringExtra("title");
        imageUrl = getIntent().getStringExtra("imageUrl");
        content = getIntent().getStringExtra("content");
        pubDate = getIntent().getStringExtra("pubDate");
        category = getIntent().getStringExtra("category");
        link = getIntent().getStringExtra("link");
    }

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

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}