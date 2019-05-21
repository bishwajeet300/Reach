package com.review.sc.view.landing;

import com.review.sc.data.model.Track;
import com.review.sc.data.model.FollowerResponse;

import java.util.LinkedList;
import java.util.List;

interface ILandingContract {

    interface ILandingPresenter {
        void fetchFollowerRecords(String userId);

        void fetchFavoriteRecords(List<FollowerResponse.CollectionBean> followers);

        void dispatchTrackDataToUI(LinkedList<Track> tracks);

        long getStoredTrackCount();

        LinkedList<Track> getAllRecords();

        LinkedList<Track> getFilteredRecords(String viewType, int limit);

        List<Track> getFilteredRecords(String genre, String value, int limit);
    }


    interface ILandingView {
        void onFollowerRecordFetchComplete(List<FollowerResponse.CollectionBean> followers);

        void onFavoriteRecordFetchComplete(LinkedList<Track> favorites);

        void onError(String errorMessage);
    }
}
