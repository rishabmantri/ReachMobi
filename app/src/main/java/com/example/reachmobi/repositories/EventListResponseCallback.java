package com.example.reachmobi.repositories;

import com.example.reachmobi.models.Event;

import java.util.List;

import androidx.annotation.NonNull;

public interface EventListResponseCallback {

    void onUpcomingEventListSuccess(@NonNull List<Event> value);

    void onUpcomingEventListError(@NonNull Throwable throwable);

    void onPreviousEventListSuccess(@NonNull List<Event> value);

    void onPreviousEventListError(@NonNull Throwable throwable);
}
