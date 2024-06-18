package com.example.codex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<myViewHolder> {

    Context context;
    List<score> lists;


    public MyAdapter(Context context, List<score> lists) {
        this.context = context;
        this.lists = lists;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.team.setText(lists.get(position).getTeam_name());
        holder.score.setText(lists.get(position).getScore_no());
        holder.timing.setText(lists.get(position).getEnd_time());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
