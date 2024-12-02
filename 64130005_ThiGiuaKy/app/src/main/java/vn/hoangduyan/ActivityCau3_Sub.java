package vn.hoangduyan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityCau3_Sub extends RecyclerView.Adapter<ActivityCau3_Sub.ViewHolder>{
    private List<QuocGia> data;
    private OnItemClickListener listener;

    public ActivityCau3_Sub(List<QuocGia> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cau3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuocGia qg = data.get(position);
        holder.txtTitle.setText(qg.getName());
        holder.txtSubTitle.setText(qg.getPop());
        holder.imageView.setImageResource(qg.imageResId);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(qg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtSubTitle;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            //sửa cái này
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSubTitle = itemView.findViewById(R.id.txtSubTitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
