package com.nicootech.procoregit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PRsAdapter extends RecyclerView.Adapter<PRsAdapter.MViewHolder> {

    private List<Model>models;
    public PRsAdapter(List<Model>models)
    {
        this.models = models;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pulls_list,parent,false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int i) {
        holder.title.setText("TITLE : "+models.get(i).getTitle());
        holder.id.setText("ISSUE #"+models.get(i).getId());
        holder.number.setText("ID #"+models.get(i).getNumber());
        holder.state.setText("STATUS #"+models.get(i).getState());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout pulls_items;
        private TextView title;
        private TextView number;
        private TextView id;
        private TextView state;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);

            pulls_items=itemView.findViewById(R.id.pulls_items);
            title = itemView.findViewById(R.id.layout_title);
            number = itemView.findViewById(R.id.layout_number);
            id = itemView.findViewById(R.id.layout_id);
            state = itemView.findViewById(R.id.layout_state);
        }
    }
}
