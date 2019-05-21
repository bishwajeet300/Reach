package com.review.sc.data.model;

import java.util.List;

public class Category {

    private String heading;
    private String subHeading;
    private List<Track> tracks;


    public Category() {
    }

    public Category(String heading, String subHeading, List<Track> tracks) {
        this.heading = heading;
        this.subHeading = subHeading;
        this.tracks = tracks;
    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
