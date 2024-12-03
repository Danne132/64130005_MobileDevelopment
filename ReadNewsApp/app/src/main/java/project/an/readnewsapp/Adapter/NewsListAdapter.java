package project.an.readnewsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import project.an.readnewsapp.Activity.NewsDetailActivity;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>  {

    private final List<NewsItem> newsItems;
    private final Context context;

    public NewsListAdapter(List<NewsItem> rssItems, Context context) {
        this.newsItems = rssItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);
        holder.titleTextView.setText(newsItem.getTitle());

        // Hiển thị hình ảnh bằng Glide (thư viện tải ảnh)
        Glide.with(context)
                .load(newsItem.getImgUrl())
                .placeholder(R.drawable.place_holder) // Hình ảnh thay thế khi đang tải
                .into(holder.imageView);

//         Bắt sự kiện click vào một item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsDetailActivity.class);
            intent.putExtra("title", newsItem.getTitle());
            intent.putExtra("imageUrl", newsItem.getImgUrl());
            intent.putExtra("link", newsItem.getLink());
            intent.putExtra("pubDate", newsItem.getPupDate());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleNews);
            imageView = itemView.findViewById(R.id.imageNews);
        }
    }

}
