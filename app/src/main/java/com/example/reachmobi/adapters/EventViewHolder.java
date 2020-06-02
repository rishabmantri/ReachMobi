package com.example.reachmobi.adapters;

import android.view.View;
import android.widget.TextView;

import com.example.reachmobi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder{


        TextView teamAwayScore;
        TextView teamHomeScore;
        TextView eventDate;
        TextView teamHome;
        TextView teamAway;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            
            teamAwayScore=itemView.findViewById(R.id.away_team_score);
            teamHomeScore=itemView.findViewById(R.id.home_team_score);
            eventDate=itemView.findViewById(R.id.event_date);
            teamHome=itemView.findViewById(R.id.team_home);
            teamAway=itemView.findViewById(R.id.team_away);
        }

    }


