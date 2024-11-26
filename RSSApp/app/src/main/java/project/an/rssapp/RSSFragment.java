package project.an.rssapp;

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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RSSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RSSFragment extends Fragment {

    private static final String ARG_URL = "rss_url";
    private String rssUrl;

    public static RSSFragment newInstance(String url) {
        RSSFragment fragment = new RSSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rssUrl = getArguments().getString(ARG_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tải dữ liệu RSS
        new Thread(() -> {
            try {
                String rssData = MainActivity.fetchRSS(rssUrl);
                List<RSSItem> rssItems = MainActivity.parseRSS(rssData);

                getActivity().runOnUiThread(() -> {
                    RSSAdapter adapter = new RSSAdapter(rssItems, getContext());
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