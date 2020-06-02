package com.example.reachmobi.viewModels;

import androidx.lifecycle.*;

import com.example.reachmobi.models.Team;
import com.example.reachmobi.repositories.TeamRepository;

import java.util.List;

public class TeamListViewModel extends ViewModel{
    private TeamRepository teamRepository;
    private LiveData<List<Team>> teamList;

    public void init(){
        if(teamList != null){
            return;
        }
        teamRepository = TeamRepository.getInstance();
        this.teamList = new MutableLiveData<>();
    }

    public LiveData<List<Team>> getTeamList(String teamName) {
        teamList= teamRepository.getTeamList(teamName);
        return teamList;
    }

}
