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

import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>  {

    private final List<NewsItem> rssItems;
    private final Context context;

    public NewsListAdapter(List<NewsItem> rssItems, Context context) {
        this.rssItems = rssItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsListAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new RSSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RSSViewHolder holder, int position) {
        NewsItem newsItem = rssItems.get(position);
        holder.titleTextView.setText(newsItem.getTitle());

        // Hiển thị hình ảnh bằng Glide (thư viện tải ảnh)
        Glide.with(context)
                .load(newsItem.getImgUrl())
                .placeholder(R.drawable.place_holder) // Hình ảnh thay thế khi đang tải
                .into(holder.imageView);

        // Bắt sự kiện click vào một item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", rssItem.getTitle());
            intent.putExtra("imageUrl", rssItem.getImageUrl());
            intent.putExtra("link", rssItem.getLink());
            intent.putExtra("content", rssItem.getContent());
            intent.putExtra("pubDate", rssItem.getPubDate());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rssItems.size();
    }

    static class RSSViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public RSSViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
