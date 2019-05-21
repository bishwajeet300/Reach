package com.review.sc.view.landing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.review.sc.crawler.DefaultExecutorSupplier;
import com.review.sc.data.local.dao.TracksDAO;
import com.review.sc.data.model.FollowerResponse;
import com.review.sc.data.model.Track;
import com.review.sc.data.remote.APIServices;
import com.review.sc.di.ApplicationContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingPresenter implements ILandingContract.ILandingPresenter {

    private static final String TAG = LandingPresenter.class.getName();

    @Inject
    @ApplicationContext
    Context context;

    @Inject
    APIServices apiServices;

    @Inject
    TracksDAO mTracksDAO;

    @Inject
    ILandingContract.ILandingView mView;

    private int RESPONSE_COUNTER = 0;


    @Inject
    LandingPresenter() {

    }


    @Override
    public void fetchFollowerRecords(String userId) {

        Call<FollowerResponse> call = apiServices.getAllFollowers(userId);

        call.enqueue(new Callback<FollowerResponse>() {
            @Override
            public void onResponse(@NonNull Call<FollowerResponse> call, @NonNull Response<FollowerResponse> response) {
                if(response.isSuccessful()) {
                    FollowerResponse followers = response.body();
                    if(null != followers && !followers.getCollection().isEmpty()) {
                        LinkedList<FollowerResponse.CollectionBean> followerList = followers.getCollection();

                        Collections.sort(followerList, new Comparator<FollowerResponse.CollectionBean>() {
                            @Override
                            public int compare(FollowerResponse.CollectionBean o1, FollowerResponse.CollectionBean o2) {
                                return o2.getLast_modified().compareTo(o1.getLast_modified());
                            }
                        });
                        mView.onFollowerRecordFetchComplete(followerList.subList(0, 20));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FollowerResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                mView.onError(t.getMessage());
            }
        });
    }


    @Override
    public void fetchFavoriteRecords(final List<FollowerResponse.CollectionBean> followers) {
        final LinkedList<Track> likedTracks = new LinkedList<>();
        Log.e(TAG, "fetchFavoriteRecords");

        for(FollowerResponse.CollectionBean user : followers) {

            Call<LinkedList<Track>> call = apiServices.getUserFavorites(String.valueOf(user.getId()));

            call.enqueue(new Callback<LinkedList<Track>>() {
                @Override
                public void onResponse(@NonNull Call<LinkedList<Track>> call, @NonNull Response<LinkedList<Track>> response) {
                    RESPONSE_COUNTER++;
                    if (response.isSuccessful() && null != response.body()) {

                        LinkedList<Track> fetchedResult = response.body();
                        for(final Track fetchedTrack : fetchedResult) {
                            final Track searchResult = likedTracks.stream()
                                    .filter(new Predicate<Track>() {
                                        @Override
                                        public boolean test(Track track) {
                                            return String.valueOf(fetchedTrack.getUser_id()).equals(String.valueOf(track.getId()));
                                        }
                                    })
                                    .findAny()
                                    .orElse(null);


                            if(null != searchResult) {
                                likedTracks.forEach(new Consumer<Track>() {
                                    @Override
                                    public void accept(Track t) {
                                        t.setFavoritings_count(t.getFavoritings_count() + searchResult.getFavoritings_count());
                                    }
                                });
                            } else {
                                likedTracks.add(fetchedTrack);
                            }
                        }
                    }

                    if(RESPONSE_COUNTER == followers.size()) {

                        if(mTracksDAO.addAllTracks(likedTracks)) {
                            Log.i(TAG, "Data Stored Successfully");
                        }

                        dispatchTrackDataToUI(likedTracks);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LinkedList<Track>> call, @NonNull Throwable t) {
                    RESPONSE_COUNTER++;
                    Log.e(TAG, t.getMessage());
                    mView.onError(t.getMessage());
                }
            });
            Log.e(TAG, "IN COUNT " + likedTracks.size());
        }
    }


    @Override
    public void dispatchTrackDataToUI(final LinkedList<Track> tracks) {
        DefaultExecutorSupplier.getInstance().forMainThreadTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        mView.onFavoriteRecordFetchComplete(tracks);
                    }
                });
    }


    @Override
    public long getStoredTrackCount() {
        return mTracksDAO.getDataCount();
    }


    @Override
    public LinkedList<Track> getAllRecords() {
        return mTracksDAO.getAllTracks();
    }


    @Override
    public LinkedList<Track> getFilteredRecords(String viewType, int limit) {
        if(viewType.equalsIgnoreCase(TracksDAO.GENRE)) {
            return mTracksDAO.getRawDataForGenre();
        } else {
            return mTracksDAO.getOrderedTracks(viewType, limit);
        }
    }


    @Override
    public List<Track> getFilteredRecords(String genre, String value, int limit) {
        return mTracksDAO.getFilteredTracks(genre, value, limit);
    }
}
