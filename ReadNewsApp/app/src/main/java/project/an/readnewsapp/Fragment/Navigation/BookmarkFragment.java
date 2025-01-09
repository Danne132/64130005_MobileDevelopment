package project.an.readnewsapp.Fragment.Navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import project.an.readnewsapp.R;
import project.an.readnewsapp.Service.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookmarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookmarkFragment extends Fragment {

    private RecyclerView bookmarkList;
    private LinearLayout bookmarkEmptyLayout, bookmarkNotEmptyLayout;
    private DatabaseHelper databaseHelper;

    public BookmarkFragment() {
        // Required empty public constructor
    }

    private void getControl(View view){
        bookmarkList = view.findViewById(R.id.bookmarkList);
        bookmarkEmptyLayout = view.findViewById(R.id.bookmarkEmptyLayout);
        bookmarkNotEmptyLayout = view.findViewById(R.id.bookmarkNotEmptyLayout);
        databaseHelper = DatabaseHelper.getInstance(getContext());
        checkDatabaseStatus();
    }
    // TODO: Rename and change types and number of parameters
    public static BookmarkFragment newInstance(String param1, String param2) {
        BookmarkFragment fragment = new BookmarkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getControl(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        checkDatabaseStatus();
    }

    private void checkDatabaseStatus() {
        if (!databaseHelper.isDatabaseEmpty()) {
            bookmarkNotEmptyLayout.setVisibility(View.GONE);
            bookmarkEmptyLayout.setVisibility(View.VISIBLE);
        } else {
            bookmarkNotEmptyLayout.setVisibility(View.VISIBLE);
            bookmarkEmptyLayout.setVisibility(View.GONE);
        }
    }
}