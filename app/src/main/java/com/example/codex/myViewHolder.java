package com.example.codex;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {

    TextView team,score,timing;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        team = itemView.findViewById(R.id.team_id);
        score = itemView.findViewById(R.id.score_id);
        timing=itemView.findViewById(R.id.time_id);

    }


}
