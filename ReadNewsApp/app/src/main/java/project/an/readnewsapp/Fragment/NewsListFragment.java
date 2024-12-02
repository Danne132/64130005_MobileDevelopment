package project.an.readnewsapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import project.an.readnewsapp.Adapter.NewsListAdapter;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends Fragment {

    private static final String ARG_URL = "category_url";
    private String category_url;

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
            category_url = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tải dữ liệu RSS
        new Thread(() -> {
            try {
                String rssData = MainActivity.fetchRSS(rssUrl);
                List<NewsItem> rssItems = MainActivity.parseRSS(rssData);

                getActivity().runOnUiThread(() -> {
                    NewsListAdapter adapter = new NewsListAdapter(rssItems, getContext());
                    recyclerView.setAdapter(adapter);
                });
                Log.d("RSSFragment", "RSS Data: " + rssData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }
}