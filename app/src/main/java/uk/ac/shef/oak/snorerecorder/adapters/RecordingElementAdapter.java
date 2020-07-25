package uk.ac.shef.oak.snorerecorder.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.shef.oak.snorerecorder.R;
import uk.ac.shef.oak.snorerecorder.models.RecordingEntity;
import uk.ac.shef.oak.snorerecorder.viewmodels.RecordingViewModel;
import uk.ac.shef.oak.snorerecorder.views.DetailActivity;

public class RecordingElementAdapter extends RecyclerView.Adapter<RecordingElementAdapter.ViewHolder>{

    private Context context;
    private List<RecordingEntity> recordingEntities = new ArrayList<>();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final RecordingEntity currentRecording = recordingEntities.get(position);

        holder.dateTextView.setText(currentRecording.getDate());
        holder.durationTextView.setText(currentRecording.getDuration());
        holder.iconImageView.setImageResource(R.drawable.ic_record);

        /**
         * On Click Listener, to the detail page
         */
        holder.dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = currentRecording.getId();
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("recordingId",id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.durationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = currentRecording.getId();
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("recordingId",id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = currentRecording.getId();
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("recordingId",id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordingEntities.size();
    }

    public void setRecordingEntities (List<RecordingEntity> recordingEntities) {
        this.recordingEntities = recordingEntities;
        /**
         * for simplicity, will be changed
         */
        notifyDataSetChanged();
    }

    /**
     * @param position is the position we swipe
     * @return recordingEntities object at this position
     */
    public RecordingEntity getRecordingEntityAt(int position){
        return recordingEntities.get(position);
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
