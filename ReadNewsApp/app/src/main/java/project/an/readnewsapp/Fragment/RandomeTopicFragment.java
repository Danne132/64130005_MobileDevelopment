package project.an.readnewsapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.an.readnewsapp.Adapter.NewsListAdapter;
import project.an.readnewsapp.Fragment.Navigation.HomeFragment;
import project.an.readnewsapp.Models.NewsItem;
import project.an.readnewsapp.R;
import project.an.readnewsapp.RSSUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomeTopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomeTopicFragment extends Fragment {

    private static final String ARG_POSITION = "list_news";
    private RecyclerView recyclerViewAllNews;
    private NewsListAdapter adapter;

    public static RandomeTopicFragment newInstance() {
        RandomeTopicFragment fragment = new RandomeTopicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_randome_topic, container, false);
        recyclerViewAllNews = view.findViewById(R.id.recyclerViewAllNews);
        recyclerViewAllNews.setLayoutManager(new LinearLayoutManager(getContext()));
        // Tải dữ liệu RSS
        new Thread(() -> {
            try {
                String rssData = RSSUtils.fetchRSS("");
                List<NewsItem> rssItems = HomeFragment.newsList;

                getActivity().runOnUiThread(() -> {
                    NewsListAdapter adapter = new NewsListAdapter(rssItems, getContext(), null);
                    recyclerViewAllNews.setAdapter(adapter);
                });
                Log.d("RSSFragment", "RSS Data: " + rssData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}