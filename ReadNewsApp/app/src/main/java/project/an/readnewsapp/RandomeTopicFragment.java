package project.an.readnewsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomeTopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomeTopicFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    public static RandomeTopicFragment newInstance(int position) {
        RandomeTopicFragment fragment = new RandomeTopicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
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
        int position = getArguments() != null ? getArguments().getInt(ARG_POSITION) : 0;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = getArguments() != null ? getArguments().getInt(ARG_POSITION) : 0;
    }
}