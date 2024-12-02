package project.an.readnewsapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import project.an.readnewsapp.Fragment.NewsListFragment;
import project.an.readnewsapp.Fragment.RandomeTopicFragment;
import project.an.readnewsapp.Models.Categories;

public class CategoryViewPageAdapter extends FragmentStateAdapter {
    private final List<Categories> categories;
    public CategoryViewPageAdapter(@NonNull Fragment fragment, List<Categories> categories) {
        super(fragment);
        this.categories = categories;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String url = categories.get(position).getUrl();
        return NewsListFragment.newInstance(url);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }
}
