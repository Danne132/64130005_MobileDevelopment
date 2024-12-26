package project.an.readnewsapp;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import project.an.readnewsapp.Models.NewsItem;

public class NewsDiffCallBack extends DiffUtil.Callback {
    private List<NewsItem> oldList;
    private List<NewsItem> newList;

    public NewsDiffCallBack(List<NewsItem> oldList, List<NewsItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getLink().equals(newList.get(newItemPosition).getLink());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
