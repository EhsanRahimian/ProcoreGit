package com.nicootech.procoregit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.MyViewHolder> {
    private List<String>cards;

    public DiffAdapter(Context context,List<String> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parents, int i) {
        View view = LayoutInflater.from(parents.getContext()).
                inflate(R.layout.show_diff,parents, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        holder.info.setText("List of Differences");

    }

    @Override
    public int getItemCount() { return cards.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView info;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.text_diff);
        }
    }
}
