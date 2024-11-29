package project.an.rssapp;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titleTextView = findViewById(R.id.detailTitleTextView);
        ImageView imageView = findViewById(R.id.detailImageView);
        TextView contentTextView = findViewById(R.id.detailContentTextView);
        TextView pubDateTextView = findViewById(R.id.detailPubDateTextView);

        // Nhận dữ liệu từ intent
        String title = getIntent().getStringExtra("title");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String content = getIntent().getStringExtra("content");
        String pubDate = getIntent().getStringExtra("pubDate");

        //Chuyển hóa các thẻ HTML
        Spanned plainText = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);

        // Gán dữ liệu vào giao diện
        titleTextView.setText(title);
        pubDateTextView.setText(pubDate);
        contentTextView.setText(plainText);
        Glide.with(this).load(imageUrl).into(imageView);
    }
}
