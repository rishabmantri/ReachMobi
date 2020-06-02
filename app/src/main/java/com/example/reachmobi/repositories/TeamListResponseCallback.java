package com.example.reachmobi.repositories;

import com.example.reachmobi.models.Team;

import java.util.List;

import androidx.annotation.NonNull;

public interface TeamListResponseCallback {

    void onTeamListSuccess(@NonNull List<Team> value);

    void onTeamListError(@NonNull Throwable throwable);
}
