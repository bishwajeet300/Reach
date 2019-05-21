package com.review.sc.data.remote;

import com.review.sc.data.model.FollowerResponse;
import com.review.sc.data.model.Track;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIServices {

    @GET("/users/{user_id}/followers?client_id=18zFL6PPbFtf5MqbKEFzGrDWDssKbODa")
    Call<FollowerResponse> getAllFollowers(@Path(value = "user_id", encoded = true) String userId);


    @GET("/users/{user_id}/favorites?client_id=18zFL6PPbFtf5MqbKEFzGrDWDssKbODa")
    Call<LinkedList<Track>> getUserFavorites(@Path(value = "user_id", encoded = true) String userId);
}
