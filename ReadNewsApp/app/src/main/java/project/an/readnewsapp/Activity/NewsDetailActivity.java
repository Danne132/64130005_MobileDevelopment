package project.an.readnewsapp.Activity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import project.an.readnewsapp.R;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView imageDetailNews, backToHome;
    TextView titleDetailTxt, categoryDetail, contentDetail, pubDateDetail;

    private void getControl(){
        imageDetailNews = findViewById(R.id.imageDetailNews);
        backToHome = findViewById(R.id.backToHome);
        titleDetailTxt = findViewById(R.id.titleDetailTxt);
        categoryDetail = findViewById(R.id.categoryDetail);
        contentDetail = findViewById(R.id.contentDetail);
        pubDateDetail = findViewById(R.id.pubDateDetail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getControl();
        // Nhận dữ liệu từ intent
        String title = getIntent().getStringExtra("title");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String content = getIntent().getStringExtra("content");
        String pubDate = getIntent().getStringExtra("pubDate");
        String category = getIntent().getStringExtra("category");
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
        // Gán dữ liệu vào giao diện
    }
}