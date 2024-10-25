package d.an.customlistviewtest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Country> {
    Activity context;
    ArrayList<Country> countryArrayList;
    int customLayout;

    public MyAdapter(Activity context, ArrayList<Country> countryArrayList, int customLayout) {
        super(context, customLayout, countryArrayList);
        this.context = context;
        this.countryArrayList = countryArrayList;
        this.customLayout = customLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // tao de chua layout
        LayoutInflater myFlater = context.getLayoutInflater();
        // dua layout vao de
        convertView = myFlater.inflate(customLayout, null);
        //lay 1 phone trong mang dua vao position
        Country myCountry = countryArrayList.get(position);
        // khai bao, anh xa id va set thuoc tinh len layout
        ImageView imgPhone = convertView.findViewById(R.id.imageViewCountry);
        imgPhone.setImageResource(myCountry.getAvatarCountry());
        TextView txtCountry = convertView.findViewById(R.id.textViewCountry);
        txtCountry.setText(myCountry.getNameCountry());
        TextView txtPop = convertView.findViewById(R.id.textViewPop);
        txtPop.setText(myCountry.getPoplation());
        return convertView;
    }
}
