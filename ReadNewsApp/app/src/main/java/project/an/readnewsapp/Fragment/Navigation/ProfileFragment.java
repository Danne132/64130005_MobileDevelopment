package project.an.readnewsapp.Fragment.Navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import project.an.readnewsapp.R;
import project.an.readnewsapp.Service.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private SeekBar seekBarTextSize;
    private TextView textSizeNumber, checkTextSize;
    private SharedPreferences sharedTextSize;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBarTextSize = view.findViewById(R.id.seekBarTextSize);
        textSizeNumber = view.findViewById(R.id.textSizeNumber);
        checkTextSize = view.findViewById(R.id.checkTextSize);
        sharedTextSize = view.getContext().getSharedPreferences("ReadNews", Context.MODE_PRIVATE);
        int textSize = sharedTextSize.getInt("textSize", 16);
        textSizeNumber.setText(String.valueOf(textSize));
        checkTextSize.setTextSize(textSize);
        seekBarTextSize.setProgress(textSize);
        seekBarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int newSize = progress;
                SharedPreferences.Editor editor = sharedTextSize.edit();
                textSizeNumber.setText(String.valueOf(newSize));
                checkTextSize.setTextSize(newSize);
                editor.putInt("textSize", newSize);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}