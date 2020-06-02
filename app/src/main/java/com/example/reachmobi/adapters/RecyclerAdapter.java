package com.example.reachmobi.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.reachmobi.models.Team;
import com.example.reachmobi.R;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Team> mTeams;
    private Context mContext;
    OnTeamListener mOnRecipeListener;

    public RecyclerAdapter(OnTeamListener mOnRecipeListener) {
        this.mOnRecipeListener = mOnRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_list_item, viewGroup, false);
        TeamViewHolder vh = new TeamViewHolder(view,mOnRecipeListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((TeamViewHolder)viewHolder).teamName.setText(mTeams.get(i).getStrTeam());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(viewHolder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(mTeams.get(i).getStrTeamBadge())
                .into(((TeamViewHolder) viewHolder).teamLogo);

    }

    @Override
    public int getItemCount() {
        if(mTeams==null)
        {
            return 0;
        }
        return mTeams.size();
    }

    public void setTeams(List<Team> Teams) {
        mTeams = Teams;
        notifyDataSetChanged();
    }

    public Team getSelectedTeam(int position){
        if(mTeams!=null){
            if(mTeams.size() > 0){
                return mTeams.get(position);
            }
        }
        return null;
    }
}