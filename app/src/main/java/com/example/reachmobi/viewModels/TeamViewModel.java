package com.example.reachmobi.viewModels;

import com.example.reachmobi.models.Event;
import com.example.reachmobi.models.Team;
import com.example.reachmobi.repositories.TeamRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamViewModel extends ViewModel {

    private TeamRepository teamRepository;
    private String teamId;
    private LiveData<List<Event>> upComingEventList;
    private LiveData<List<Event>> previousEventList;

    public TeamViewModel(){
        this.teamRepository = TeamRepository.getInstance();
        this.upComingEventList = new MutableLiveData<>();
        this.previousEventList = new MutableLiveData<>();
    }

    public LiveData<List<Event>> getupComingEventList(String teamId) {
        this.upComingEventList= teamRepository.getupComingEventList(teamId);
        return this.upComingEventList;
    }

    public LiveData<Team> getTeam(){
        return this.teamRepository.getTeam();
    }

    public String getTeamId(){
        return this.teamId;
    }

    public void searchTeamById(String teamId) {
        this.teamId = teamId;
        this.teamRepository.getTeamById(teamId);
    }

    public  LiveData<List<Event>> getupComingEventList() {
        return this.upComingEventList;
    }


    public LiveData<List<Event>> getpreviousEventList(String teamId) {
        this.previousEventList= teamRepository.getpreviuosEventList(teamId);
        return this.previousEventList;
    }

    public  LiveData<List<Event>> getpreviousEventList() {
        return this.previousEventList;
    }

}
