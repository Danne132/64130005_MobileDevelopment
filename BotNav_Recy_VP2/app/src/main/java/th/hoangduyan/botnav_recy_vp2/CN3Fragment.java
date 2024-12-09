package th.hoangduyan.botnav_recy_vp2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CN3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CN3Fragment extends Fragment {

    private ViewPager2 viewPager;
    private Button btn1, btn2, btn3;

    public CN3Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CN3Fragment newInstance(String param1, String param2) {
        CN3Fragment fragment = new CN3Fragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_c_n3, container, false);
        viewPager = view.findViewById(R.id.view_pager);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);


        // Danh sách item hiển thị
        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");

        // Gán adapter
        ViewPageAdapter adapter = new ViewPageAdapter(items);
        viewPager.setAdapter(adapter);

        // Xử lý sự kiện nút bấm
        btn1.setOnClickListener(v -> viewPager.setCurrentItem(0));
        btn2.setOnClickListener(v -> viewPager.setCurrentItem(1));
        btn3.setOnClickListener(v -> viewPager.setCurrentItem(2));

        return view;
    }
}