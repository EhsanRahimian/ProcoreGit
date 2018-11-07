package com.nicootech.procoregit;

import android.content.Context;
import android.content.Intent;
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
    private Context context;

    public PRsAdapter( Context currentContext, List<Model>prListFromActivity)
    {
        context = currentContext;
        this.models = prListFromActivity;
    }

    @NonNull
    @Override
    public PRsAdapter.MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pulls_list, parent, false);

        return new PRsAdapter.MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int i) {

        final Model currentListItem = models.get(i);

        holder.title.setText("TITLE : "+currentListItem.getTitle());
        holder.id.setText("ID # "+currentListItem.getId());
        holder.number.setText("NUMBER # "+currentListItem.getNumber());
        holder.state.setText("STATE : "+currentListItem.getState());

        holder.pulls_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DiffActivity.class);
                intent.putExtra("diffUrlFromIntent",extractPRs(currentListItem.getDiff_url()));

                context.startActivity(intent);
            }
        });

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
    private String extractPRs(String str) {
        String[] slashRemove = str.split("/");
        return slashRemove[slashRemove.length - 1];
    }
}
