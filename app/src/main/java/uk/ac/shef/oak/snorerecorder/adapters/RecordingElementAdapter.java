package uk.ac.shef.oak.snorerecorder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.shef.oak.snorerecorder.R;
import uk.ac.shef.oak.snorerecorder.models.RecordingElement;
import uk.ac.shef.oak.snorerecorder.views.DetailActivity;

public class RecordingElementAdapter extends RecyclerView.Adapter<RecordingElementAdapter.ViewHolder>{
    private Context context;
    private RecordingElement[] items;

    public RecordingElementAdapter(RecordingElement[] items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(holder!=null && items[position]!=null) {
           holder.dateTextView.setText(items[position].getDate());
           holder.durationTextView.setText((items[position].getDuration()));
           holder.iconImageView.setImageResource(R.drawable.recording_icon);
        }

        /**
         * On Click Listener, to the detail page
         */
        holder.dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.durationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateTextView;
        TextView durationTextView;
        ImageView iconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            durationTextView = itemView.findViewById(R.id.durationTextView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
        }
    }

}
