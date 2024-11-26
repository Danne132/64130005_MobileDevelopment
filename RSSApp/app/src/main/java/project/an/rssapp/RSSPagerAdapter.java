package project.an.rssapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class RSSPagerAdapter extends FragmentStateAdapter {
    private final List<RSSCategory> categories;

    public RSSPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<RSSCategory> categories) {
        super(fragmentActivity);
        this.categories = categories;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String url = categories.get(position).getUrl();
        return RSSFragment.newInstance(url);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
