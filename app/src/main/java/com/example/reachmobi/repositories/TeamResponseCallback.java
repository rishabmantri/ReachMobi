package com.example.reachmobi.repositories;

import com.example.reachmobi.models.Team;

import androidx.annotation.NonNull;

public interface TeamResponseCallback {
    void onTeamSuccess(@NonNull Team value);

    void onTeamError(@NonNull Throwable throwable);
}
