package com.example.reachmobi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reachmobi.adapters.OnTeamListener;
import com.example.reachmobi.adapters.RecyclerAdapter;
import com.example.reachmobi.models.Team;
import com.example.reachmobi.viewModels.TeamListViewModel;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScrollingActivity extends BaseActivity implements OnTeamListener {
    private static final String TAG = "MainActivity";
    private RecyclerView teamListView;
    private RecyclerAdapter teamAdapter;
    private TeamListViewModel teamListViewModel;
    private Context context;
    private SearchView searchView;
    private TextView teamEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);
        init();

        teamListViewModel = ViewModelProviders.of(this).get(TeamListViewModel.class);
        teamListViewModel.init();

        initRecyclerView();

//      Teamlist observer
        teamListViewModel.getTeamList("").observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> Teams) {
                teamAdapter.setTeams(Teams);
                teamAdapter.notifyDataSetChanged();
                if(teamAdapter != null && teamEmptyView != null) {
                    if(teamAdapter.getItemCount() == 0) {
                        teamEmptyView.setVisibility(View.VISIBLE);
                        teamListView.setVisibility(View.GONE);
                    }
                    else {
                        teamEmptyView.setVisibility(View.GONE);
                        teamListView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        initSearchView();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    private void initSearchView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                teamListViewModel.getTeamList(query);
                searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initRecyclerView(){
        teamAdapter = new RecyclerAdapter(this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        teamListView.setLayoutManager(linearLayoutManager);
        teamListView.setAdapter(teamAdapter);
    }

    private void init() {
        searchView = findViewById(R.id.search_view);
        teamListView = findViewById(R.id.recycler_view);
        teamEmptyView= findViewById(R.id.teams_empty_view);
    }

    @Override
    public void onTeamClick(int position) {
        Intent intent = new Intent(this , TeamActivity.class);
        intent.putExtra("team",teamAdapter.getSelectedTeam(position));
        startActivity(intent);
    }
}