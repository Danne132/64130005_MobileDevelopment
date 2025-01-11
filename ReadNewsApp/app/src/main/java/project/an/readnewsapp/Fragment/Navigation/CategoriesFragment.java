package project.an.readnewsapp.Fragment.Navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import project.an.readnewsapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {

    LinearLayout AICategoryChoose, SoftwareCategoryChoose, TechCategoryChoose, SecurityCategoryChoose;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void getControl(View view){
        AICategoryChoose = view.findViewById(R.id.AICategoryChoose);
        SoftwareCategoryChoose = view.findViewById(R.id.SoftwareCategoryChoose);
        TechCategoryChoose = view.findViewById(R.id.TechCategoryChoose);
        SecurityCategoryChoose = view.findViewById(R.id.SecurityCategoryChoose);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getControl(view);
        AICategoryChoose.setOnClickListener(aiClick);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    View.OnClickListener aiClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HomeFragment homeFragment = new HomeFragment();

            // Truyền thông tin tab thứ 2 qua Bundle
            Bundle bundle = new Bundle();
            bundle.putInt("selectedTab", 1); // Tab thứ 2 (vị trí index = 1)
            homeFragment.setArguments(bundle);

            // Chuyển sang HomeFragment
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, homeFragment) // Thay R.id.fragmentContainer bằng ID container thực tế
                    .addToBackStack(null) // Thêm vào back stack để quay lại nếu cần
                    .commit();
        }
    };

}