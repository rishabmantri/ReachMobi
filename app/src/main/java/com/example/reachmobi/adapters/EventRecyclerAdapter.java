package com.example.reachmobi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reachmobi.R;
import com.example.reachmobi.models.Event;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Event> eventList;
    private Context mContext;

    public EventRecyclerAdapter(Context context,List<Event> eventList) {
        this.eventList = eventList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item, viewGroup, false);
        EventViewHolder vh = new EventViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((EventViewHolder)viewHolder).eventDate.setText(eventList.get(i).getDateEvent());
        ((EventViewHolder)viewHolder).teamAwayScore.setText(eventList.get(i).getIntAwayScore());
        ((EventViewHolder)viewHolder).teamHomeScore.setText(eventList.get(i).getIntHomeScore());
        ((EventViewHolder)viewHolder).teamHome.setText(eventList.get(i).getStrHomeTeam());
        ((EventViewHolder)viewHolder).teamAway.setText(eventList.get(i).getStrAwayTeam());
    }

    @Override
    public int getItemCount() {
        if(this.eventList==null)
        {
            return 0;
        }
        return this.eventList.size();
    }

    public void setEventList(List<Event> events) {
        this.eventList = events;
        notifyDataSetChanged();
    }

}
