package com.example.reachmobi;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reachmobi.adapters.EventRecyclerAdapter;
import com.example.reachmobi.models.Event;
import com.example.reachmobi.models.Team;
import com.example.reachmobi.viewModels.TeamViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamActivity extends BaseActivity {
    private ImageView teamImage;
    private TextView teamTitle , comingEvents, previousEvents,upcomingEmptyview,previousEmptyview;
    private TeamViewModel teamViewModel;
    private RecyclerView upcomingTeamEvents, previousTeamEvents;
    private EventRecyclerAdapter upcomingEventAdapter, previousEventAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        this.init();

        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);

        this.initRecyclerView();
        this.getIncomingIntent();
        this.subscribeObservers();
    }

    private void init()
    {
        teamTitle = findViewById(R.id.title_team);
        comingEvents= findViewById(R.id.next_five_events);
        previousEvents= findViewById(R.id.prev_five_events);
        upcomingTeamEvents = findViewById(R.id.upcoming_events);
        previousTeamEvents = findViewById(R.id.prev_events);
        teamImage = findViewById(R.id.title_logo);
        upcomingEmptyview= findViewById(R.id.upcoming_empty_view);
        previousEmptyview= findViewById(R.id.prev_empty_view);

        comingEvents.setVisibility(View.VISIBLE);
        previousEvents.setVisibility(View.VISIBLE);
        previousEvents.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    private void initRecyclerView(){
        upcomingEventAdapter = new EventRecyclerAdapter(this,teamViewModel.getupComingEventList().getValue());
        RecyclerView.LayoutManager upcomingllm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        upcomingTeamEvents.setHasFixedSize(true);
        upcomingTeamEvents.setAdapter(upcomingEventAdapter);
        upcomingTeamEvents.setLayoutManager(upcomingllm);


        previousEventAdapter = new EventRecyclerAdapter(this,teamViewModel.getpreviousEventList().getValue());
        RecyclerView.LayoutManager previousllm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        previousTeamEvents.setHasFixedSize(true);
        previousTeamEvents.setAdapter(previousEventAdapter);
        previousTeamEvents.setLayoutManager(previousllm);
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("team")){
            Team team = getIntent().getParcelableExtra("team");
            teamViewModel.searchTeamById(team.getIdTeam());
            teamViewModel.getupComingEventList(team.getIdTeam());
            teamViewModel.getpreviousEventList(team.getIdTeam());
        }
    }

    private void subscribeObservers(){
        teamViewModel.getTeam().observe(this, new Observer<Team>() {
            @Override
            public void onChanged(@Nullable Team team) {
                if(team!=null){
                    if(team.getIdTeam().equals(teamViewModel.getTeamId())){
                        setTeamProperties(team);
                    }
                }
            }
        });

        teamViewModel.getupComingEventList("").observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                upcomingEventAdapter.setEventList(events);
                upcomingEventAdapter.notifyDataSetChanged();
                if(upcomingEventAdapter != null && upcomingEmptyview != null) {
                    if(upcomingEventAdapter.getItemCount() == 0) {
                        upcomingEmptyview.setVisibility(View.VISIBLE);
                        upcomingTeamEvents.setVisibility(View.GONE);
                    }
                    else {
                        upcomingEmptyview.setVisibility(View.GONE);
                        upcomingTeamEvents.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        teamViewModel.getpreviousEventList("").observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                previousEventAdapter.setEventList(events);
                previousEventAdapter.notifyDataSetChanged();
                if(previousEventAdapter != null && previousEmptyview != null) {
                    if(previousEventAdapter.getItemCount() == 0) {
                        previousEmptyview.setVisibility(View.VISIBLE);
                        previousTeamEvents.setVisibility(View.GONE);
                    }
                    else {
                        previousEmptyview.setVisibility(View.GONE);
                        previousTeamEvents.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void setTeamProperties(Team team){
        if(team!=null){

            teamTitle.setText(team.getStrTeam());

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(team.getStrTeamBadge())
                    .into(teamImage);
        }

    }

}
