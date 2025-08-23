package com.example.appfrontend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfrontend.R;
import com.example.appfrontend.model.PeriodTDO;

import java.util.List;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder> {

    private final List<PeriodTDO> periods;

    public PeriodAdapter(List<PeriodTDO> periods) {
        this.periods = periods;
    }

    @NonNull
    @Override
    public PeriodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_period, parent, false);
        return new PeriodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeriodViewHolder holder, int position) {
        PeriodTDO period = periods.get(position);
        holder.textViewPeriodName.setText(period.getName_period());
    }

    @Override
    public int getItemCount() {
        return periods.size();
    }

    static class PeriodViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPeriodName;

        public PeriodViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPeriodName = itemView.findViewById(R.id.textViewPeriodName);
        }
    }
}
