package com.review.sc.model;

import java.util.List;

public class User {

    private String avatar_url;
    private int id;
    private String kind;
    private String permalink_url;
    private String uri;
    private String username;
    private String permalink;
    private String last_modified;
    private String first_name;
    private String last_name;
    private String full_name;
    private String city;
    private String description;
    private String country;
    private int track_count;
    private int public_favorites_count;
    private int followers_count;
    private int followings_count;
    private String plan;
    private String myspace_name;
    private String discogs_name;
    private String website_title;
    private String website;
    private int reposts_count;
    private boolean online;
    private int playlist_count;
    private int comments_count;
    private int likes_count;
    private List<SubscriptionsBean> subscriptions;

    public User() {
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPermalink_url() {
        return permalink_url;
    }

    public void setPermalink_url(String permalink_url) {
        this.permalink_url = permalink_url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTrack_count() {
        return track_count;
    }

    public void setTrack_count(int track_count) {
        this.track_count = track_count;
    }

    public int getPublic_favorites_count() {
        return public_favorites_count;
    }

    public void setPublic_favorites_count(int public_favorites_count) {
        this.public_favorites_count = public_favorites_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollowings_count() {
        return followings_count;
    }

    public void setFollowings_count(int followings_count) {
        this.followings_count = followings_count;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getMyspace_name() {
        return myspace_name;
    }

    public void setMyspace_name(String myspace_name) {
        this.myspace_name = myspace_name;
    }

    public String getDiscogs_name() {
        return discogs_name;
    }

    public void setDiscogs_name(String discogs_name) {
        this.discogs_name = discogs_name;
    }

    public String getWebsite_title() {
        return website_title;
    }

    public void setWebsite_title(String website_title) {
        this.website_title = website_title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getPlaylist_count() {
        return playlist_count;
    }

    public void setPlaylist_count(int playlist_count) {
        this.playlist_count = playlist_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public List<SubscriptionsBean> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionsBean> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public static class SubscriptionsBean {
        /**
         * product : {"id":"creator-pro","name":"Pro"}
         */

        private ProductBean product;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * id : creator-pro
             * name : Pro
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
