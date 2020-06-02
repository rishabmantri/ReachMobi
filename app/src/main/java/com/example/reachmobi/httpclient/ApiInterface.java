package com.example.reachmobi.httpclient;

import com.example.reachmobi.models.EventResponse;
import com.example.reachmobi.models.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("searchteams.php")
    Call<TeamResponse> getsearchTeam(@Query("t") String id);

    @GET("lookupteam.php?")
    Call<TeamResponse> getLookupTeam(@Query("id") String id);

    @GET("eventsnext.php")
    Call<EventResponse> getNextEventResponse(@Query("id") String id);

    @GET("eventslast.php")
    Call<EventResponse> getLastEventResponse(@Query("id") String id);


}
