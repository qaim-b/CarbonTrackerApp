package com.example.carbonegy2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmissionRecordRvAdapter extends RecyclerView.Adapter<EmissionRecordRvAdapter.EmissionRecordViewHolder> {

    private List<EmissionRecord> emissionRecordList;
    private Context context;

    public EmissionRecordRvAdapter(List<EmissionRecord> emissionRecordList, Context context) {
        this.emissionRecordList = emissionRecordList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmissionRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emission_record_item, parent, false);
        return new EmissionRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmissionRecordViewHolder holder, int position) {
        EmissionRecord emissionRecord = emissionRecordList.get(position);
        holder.dateTextView.setText(emissionRecord.getDate());
        holder.emissionValueTextView.setText(Integer.toString(emissionRecord.getValue())+ "Kg");
    }

    @Override
    public int getItemCount() {
        return emissionRecordList.size();
    }

    class EmissionRecordViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView emissionValueTextView;

        public EmissionRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            emissionValueTextView = itemView.findViewById(R.id.emission_value_text_view);
        }
    }

}
