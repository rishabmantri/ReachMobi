package com.example.reachmobi.repositories;

import com.example.reachmobi.httpclient.ApiClient;
import com.example.reachmobi.httpclient.ApiInterface;
import com.example.reachmobi.models.Event;
import com.example.reachmobi.models.EventResponse;
import com.example.reachmobi.models.TeamResponse;
import com.example.reachmobi.models.Team;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamRepository implements TeamListResponseCallback,TeamResponseCallback, EventListResponseCallback{
    private static TeamRepository instance;
    private MutableLiveData<List<Team>> TeamList;
    private MutableLiveData<List<Event>> upComingEventList;
    private MutableLiveData<List<Event>> previousEventList;
    MutableLiveData<Team> Team;

    public static TeamRepository getInstance(){
        if(instance == null){
            instance = new TeamRepository();
        }
        return instance;
    }

    public TeamRepository()
    {
        this.TeamList = new MutableLiveData<>();
        this.Team = new MutableLiveData<>();
        this.upComingEventList = new MutableLiveData<>();
        this.previousEventList = new MutableLiveData<>();
    }


    public void getTeams(String teamName, @Nullable final TeamListResponseCallback callbacks) {
        if(teamName==null|| teamName.equals(""))
        {
            return;
        }
        ApiInterface apiService = ApiClient.getApiClient().create(ApiInterface.class);
        Call<TeamResponse> call = apiService.getsearchTeam(teamName);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {

                List<Team> teams = response.body().getTeams();
                if (callbacks != null) {
                    callbacks.onTeamListSuccess(teams);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                if (callbacks != null) {
                    callbacks.onTeamListError(t);
                }
            }
        });
    }

    public void getTeamById(String teamId, @Nullable final TeamResponseCallback callbacks) {
        if(teamId==null|| teamId.equals(""))
        {
            return;
        }
        ApiInterface apiService = ApiClient.getApiClient().create(ApiInterface.class);
        Call<TeamResponse> call = apiService.getLookupTeam(teamId);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                Team team = response.body().getTeams().get(0);
                if (callbacks != null) {
                    callbacks.onTeamSuccess(team);
                }

            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                if (callbacks != null) {
                    callbacks.onTeamError(t);
                }
            }
        });
    }

    public void getupComingEvents(String teamId, @Nullable final EventListResponseCallback callbacks) {
        if(teamId==null|| teamId.equals(""))
        {
            return;
        }

        ApiInterface apiService = ApiClient.getApiClient().create(ApiInterface.class);
        Call<EventResponse> call = apiService.getNextEventResponse(teamId);

        call.enqueue(new Callback<EventResponse>() {

            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                List<Event> events = response.body().getEvents();
              if (callbacks != null) {
                    callbacks.onUpcomingEventListSuccess(events);
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                if (callbacks != null) {
                    callbacks.onUpcomingEventListError(t);
                }
            }
        });
    }

    public void getpreviousEvents(String teamId, @Nullable final EventListResponseCallback callbacks) {
        if(teamId==null|| teamId.equals(""))
        {
            return;
        }

        ApiInterface apiService = ApiClient.getApiClient().create(ApiInterface.class);
        Call<EventResponse> call = apiService.getLastEventResponse(teamId);

        call.enqueue(new Callback<EventResponse>() {

            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                List<Event> events = response.body().getEvents();
                if (callbacks != null) {
                    callbacks.onPreviousEventListSuccess(events);
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                if (callbacks != null) {
                    callbacks.onPreviousEventListError(t);
                }
            }
        });
    }

    public LiveData<List<Event>> getupComingEventList(String teamId) {
        getupComingEvents(teamId,this);
        return upComingEventList;
    }

    public LiveData<List<Event>> getpreviuosEventList(String teamId) {
        getpreviousEvents(teamId,this);
        return previousEventList;
    }

    public LiveData<Team> getTeam(){
        return Team;
    }

    public LiveData<Team> getTeamById(String teamId) {
        getTeamById(teamId,this);
        return Team;
    }

    public LiveData<List<Team>> getTeamList(String teamName) {
        getTeams(teamName,this);
        return TeamList;
    }

//   callback functions to set value in livedata elements
    @Override
    public void onTeamListSuccess(@NonNull List<Team> value) {
        this.TeamList.setValue(value);
    }

    @Override
    public void onTeamListError(@NonNull Throwable throwable) {
    }

    @Override
    public void onTeamSuccess(@NonNull Team value) {
        this.Team.setValue(value);
    }

    @Override
    public void onTeamError(@NonNull Throwable throwable) {
    }


    @Override
    public void onUpcomingEventListSuccess(@NonNull List<Event> value) {
        this.upComingEventList.setValue(value);
    }

    @Override
    public void onUpcomingEventListError(@NonNull Throwable throwable) {
    }

    @Override
    public void onPreviousEventListSuccess(@NonNull List<Event> value) {
        this.previousEventList.setValue(value);
    }

    @Override
    public void onPreviousEventListError(@NonNull Throwable throwable) {
    }
}

