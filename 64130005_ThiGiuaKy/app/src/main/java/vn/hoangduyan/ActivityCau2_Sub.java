package vn.hoangduyan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityCau2_Sub extends ArrayAdapter<String> {
    public ActivityCau2_Sub(Context context, ArrayList<String> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cau2, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.text_view_item);
        textView.setText(getItem(position));
        return convertView;
    }
}
