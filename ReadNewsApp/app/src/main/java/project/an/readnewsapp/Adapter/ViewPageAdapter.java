package project.an.readnewsapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import project.an.readnewsapp.Fragment.RandomeTopicFragment;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return RandomeTopicFragment.newInstance(position);
    }


    @Override
    public int getItemCount() {
        return 6; // Số lượng tab
    }
}
