package com.example.reachmobi.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reachmobi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView teamName;
    OnTeamListener onTeamsListener;
    ImageView teamLogo;

    public TeamViewHolder(@NonNull View itemView , OnTeamListener onTeamsListener) {
        super(itemView);
        this.onTeamsListener = onTeamsListener;

        teamName = itemView.findViewById(R.id.team_title);
        teamLogo = itemView.findViewById(R.id.team_logo);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      onTeamsListener.onTeamClick(getAdapterPosition());
    }
}
