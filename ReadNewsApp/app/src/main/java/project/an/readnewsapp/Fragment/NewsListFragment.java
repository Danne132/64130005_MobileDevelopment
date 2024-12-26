package project.an.readnewsapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import project.an.readnewsapp.Adapter.NewsListAdapter;
import project.an.readnewsapp.Fragment.Navigation.HomeFragment;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;
import project.an.readnewsapp.RSSUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends Fragment {

    private static final String ARG_URL = "category_url";
    private String categoryUrl;
    private RecyclerView recyclerView;
    private NewsListAdapter adapter;
    private boolean isLoading = false;

    public static NewsListFragment newInstance(String url) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryUrl = getArguments().getString(ARG_URL);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Tải dữ liệu RSS
        new Thread(() -> {
            try {
                String rssData = RSSUtils.fetchRSS(categoryUrl);
                List<NewsItem> rssItems = RSSUtils.parseRSS(rssData);

                getActivity().runOnUiThread(() -> {
                    NewsListAdapter adapter = new NewsListAdapter(rssItems, getContext());
                    recyclerView.setAdapter(adapter);
                });
                Log.d("RSSFragment", "RSS Data: " + rssData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return view;
    }

}