package com.nicootech.procoregit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import java.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.MyViewHolder> {

    private List<String>cards;

    public DiffAdapter(List<String>diff_content ){
        this.cards=diff_content;
    }
    @NonNull
    @Override
    public DiffAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.show_diff, viewGroup,false);

        return new DiffAdapter.MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull DiffAdapter.MyViewHolder holder, int i) {

        if(i!=0){
            holder.infoUrlDiff.setText(stringProcess(i));
        }else{
            holder.infoUrlDiff.setText("Differences");
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView infoUrlDiff;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            infoUrlDiff = itemView.findViewById(R.id.text_diff);
        }

    }

   /* private SpannableStringBuilder stringProcess(int position){
        String[] individualLineStrings = cards.get(position).split("\n");
        SpannableStringBuilder SBuilder = new SpannableStringBuilder();
        for(String curStr: individualLineStrings){
            SpannableString s = new SpannableString(curStr);
            SBuilder.append(s);
            SBuilder.append("\n");
        }
        return SBuilder;
    }
    */
   private  StringBuffer stringProcess(int position) {

       String[] individualLineStrings = cards.get(position).split("\n");

       StringBuffer SBuffer = new StringBuffer();

       for (String curStr : individualLineStrings) {

           if (curStr != null && curStr.length() > 0 && curStr.charAt(0) != '+') {
               String s = new String(curStr);
               SBuffer.append(s);
               SBuffer.append("\n");
           }

       }
       return SBuffer;

   }


}
