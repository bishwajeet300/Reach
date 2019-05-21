package com.review.sc.data.local.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.review.sc.data.local.AbstractDatabaseManager;
import com.review.sc.data.local.DatabaseManager;
import com.review.sc.data.model.Track;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class TracksDAO extends AbstractDatabaseManager<Track> {

    public static String TABLE_NAME = "TABLE_TRACKS";

    private static final String KIND = "kind";
    private static final String ID = "id";
    private static final String CREATED_AT = "created_at";
    private static final String USER_ID = "user_id";
    private static final String DURATION = "duration";
    private static final String COMMENTABLE = "commentable";
    private static final String STATE = "state";
    private static final String ORIGINAL_CONTENT_SIZE = "original_content_size";
    private static final String LAST_MODIFIED = "last_modified";
    private static final String SHARING = "sharing";
    private static final String TAG_LIST = "tag_list";
    private static final String PERMALINK = "permalink";
    private static final String STREAMABLE = "streamable";
    private static final String EMBEDDABLE_BY = "embeddable_by";
    private static final String DOWNLOADABLE = "downloadable";
    private static final String PURCHASE_URL = "purchase_url";
    private static final String LABEL_ID = "label_id";
    private static final String PURCHASE_TITLE = "purchase_title";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LABEL_NAME = "label_name";
    private static final String RELEASE = "release";
    private static final String TRACK_TYPE = "track_type";
    private static final String KEY_SIGNATURE = "key_signature";
    private static final String ISRC = "isrc";
    private static final String VIDEO_URL = "video_url";
    private static final String BPM = "bpm";
    private static final String RELEASE_DAY = "release_day";
    private static final String ORIGINAL_FORMAT = "original_format";
    private static final String LICENSE = "license";
    private static final String URI = "uri";
    private static final String USER = "user";
    private static final String ATTACHMENTS_URI = "attachments_uri";
    private static final String PERMALINK_URL = "permalink_url";
    private static final String ARTWORK_URL = "artwork_url";
    private static final String WAVEFORM_URL = "waveform_url";
    private static final String STREAM_URL = "stream_url";
    private static final String DOWNLOAD_URL = "download_url";
    private static final String DOWNLOAD_COUNT = "download_count";
    public static final String GENRE = "genre";
    public static final String RELEASE_YEAR = "release_year";
    public static final String RELEASE_MONTH = "release_month";
    public static final String PLAYBACK_COUNT = "playback_count";
    public static final String FAVORITINGS_COUNT = "favoritings_count";
    public static final String COMMENT_COUNT = "comment_count";

    @Inject
    public TracksDAO(DatabaseManager dbManager) {
        super(dbManager);
    }


    @Override
    public ContentValues generateContentValuesFromObject(Track trackBean) {
        if(null == trackBean) {
            return null;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, trackBean.getId());
            contentValues.put(KIND, trackBean.getKind());
            contentValues.put(CREATED_AT, trackBean.getCreated_at());
            contentValues.put(USER_ID, trackBean.getUser_id());
            contentValues.put(DURATION, trackBean.getDuration());
            contentValues.put(COMMENTABLE, trackBean.isCommentable());
            contentValues.put(STATE, trackBean.getState());
            contentValues.put(ORIGINAL_CONTENT_SIZE, trackBean.getOriginal_content_size());
            contentValues.put(LAST_MODIFIED, trackBean.getLast_modified());
            contentValues.put(SHARING, trackBean.getSharing());
            contentValues.put(TAG_LIST, trackBean.getTag_list());
            contentValues.put(PERMALINK, trackBean.getPermalink());
            contentValues.put(STREAMABLE, trackBean.isStreamable());
            contentValues.put(EMBEDDABLE_BY, trackBean.getEmbeddable_by());
            contentValues.put(DOWNLOADABLE, trackBean.isDownloadable());
            contentValues.put(PURCHASE_URL, trackBean.getPurchase_url());
            contentValues.put(LABEL_ID, trackBean.getLabel_id());
            contentValues.put(PURCHASE_TITLE, trackBean.getPurchase_title());
            contentValues.put(GENRE, trackBean.getGenre());
            contentValues.put(TITLE, trackBean.getTitle());
            contentValues.put(DESCRIPTION, trackBean.getDescription());
            contentValues.put(LABEL_NAME, trackBean.getLabel_name());
            contentValues.put(RELEASE, trackBean.getRelease());
            contentValues.put(TRACK_TYPE, trackBean.getTrack_type());
            contentValues.put(KEY_SIGNATURE, trackBean.getKey_signature());
            contentValues.put(ISRC, trackBean.getIsrc());
            contentValues.put(VIDEO_URL, trackBean.getVideo_url());
            contentValues.put(BPM, trackBean.getBpm());
            contentValues.put(RELEASE_YEAR, trackBean.getRelease_year());
            contentValues.put(RELEASE_MONTH, trackBean.getRelease_month());
            contentValues.put(RELEASE_DAY, trackBean.getRelease_day());
            contentValues.put(ORIGINAL_FORMAT, trackBean.getOriginal_format());
            contentValues.put(LICENSE, trackBean.getLicense());
            contentValues.put(URI, trackBean.getUri());
            contentValues.put(USER, new Gson().toJson(trackBean.getUser(), Track.UserBean.class));
            contentValues.put(ATTACHMENTS_URI, trackBean.getAttachments_uri());
            contentValues.put(PERMALINK_URL, trackBean.getPermalink_url());
            contentValues.put(ARTWORK_URL, trackBean.getArtwork_url());
            contentValues.put(WAVEFORM_URL, trackBean.getWaveform_url());
            contentValues.put(STREAM_URL, trackBean.getStream_url());
            contentValues.put(DOWNLOAD_URL, trackBean.getDownload_url());
            contentValues.put(PLAYBACK_COUNT, trackBean.getPlayback_count());
            contentValues.put(DOWNLOAD_COUNT, trackBean.getDownload_count());
            contentValues.put(FAVORITINGS_COUNT, trackBean.getFavoritings_count());
            contentValues.put(COMMENT_COUNT, trackBean.getComment_count());

            return contentValues;
        }
    }


    public static String getCreateTableQuery() {

        return "CREATE TABLE " + TABLE_NAME + "("

                + ID + " INTEGER PRIMARY KEY, "
                + KIND + " TEXT, "
                + CREATED_AT + " TEXT, "
                + USER_ID + " TEXT, "
                + DURATION + " INTEGER, "
                + COMMENTABLE + " TEXT, "
                + STATE + " TEXT, "
                + ORIGINAL_CONTENT_SIZE + " TEXT, "
                + LAST_MODIFIED + " TEXT, "
                + SHARING + " TEXT, "
                + TAG_LIST + " TEXT, "
                + PERMALINK + " TEXT, "
                + STREAMABLE + " TEXT, "
                + EMBEDDABLE_BY + " TEXT, "
                + DOWNLOADABLE + " TEXT, "
                + PURCHASE_URL + " TEXT, "
                + LABEL_ID + " TEXT, "
                + PURCHASE_TITLE + " TEXT, "
                + GENRE + " TEXT, "
                + TITLE + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + LABEL_NAME + " TEXT, "
                + RELEASE + " TEXT, "
                + TRACK_TYPE + " TEXT, "
                + KEY_SIGNATURE + " TEXT, "
                + ISRC + " TEXT, "
                + VIDEO_URL + " TEXT, "
                + BPM + " TEXT, "
                + RELEASE_YEAR + " TEXT, "
                + RELEASE_MONTH + " TEXT, "
                + RELEASE_DAY + " TEXT, "
                + ORIGINAL_FORMAT + " TEXT, "
                + LICENSE + " TEXT, "
                + URI + " TEXT, "
                + USER + " TEXT, "
                + ATTACHMENTS_URI + " TEXT, "
                + PERMALINK_URL + " TEXT, "
                + ARTWORK_URL + " TEXT, "
                + WAVEFORM_URL + " TEXT, "
                + STREAM_URL + " TEXT, "
                + DOWNLOAD_URL + " TEXT, "
                + PLAYBACK_COUNT + " INTEGER, "
                + DOWNLOAD_COUNT + " INTEGER, "
                + FAVORITINGS_COUNT + " INTEGER, "
                + COMMENT_COUNT + " INTEGER"
                + ")";
    }


    public Track generateObjectFromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        Track track = new Track();

        track.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        track.setKind(cursor.getString(cursor.getColumnIndex(KIND)));
        track.setCreated_at(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
        track.setUser_id(cursor.getInt(cursor.getColumnIndex(USER_ID)));
        track.setDuration(cursor.getInt(cursor.getColumnIndex(DURATION)));
        track.setCommentable(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COMMENTABLE))));
        track.setState(cursor.getString(cursor.getColumnIndex(STATE)));
        track.setOriginal_content_size(cursor.getInt(cursor.getColumnIndex(ORIGINAL_CONTENT_SIZE)));
        track.setLast_modified(cursor.getString(cursor.getColumnIndex(LAST_MODIFIED)));
        track.setSharing(cursor.getString(cursor.getColumnIndex(SHARING)));
        track.setTag_list(cursor.getString(cursor.getColumnIndex(TAG_LIST)));
        track.setPermalink(cursor.getString(cursor.getColumnIndex(PERMALINK)));
        track.setStreamable(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(STREAMABLE))));
        track.setEmbeddable_by(cursor.getString(cursor.getColumnIndex(EMBEDDABLE_BY)));
        track.setDownloadable(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(DOWNLOADABLE))));
        track.setPurchase_url(cursor.getString(cursor.getColumnIndex(PURCHASE_URL)));
        track.setLabel_id(cursor.getString(cursor.getColumnIndex(LABEL_ID)));
        track.setPurchase_title(cursor.getString(cursor.getColumnIndex(PURCHASE_TITLE)));
        track.setGenre(cursor.getString(cursor.getColumnIndex(GENRE)));
        track.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        track.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        track.setLabel_name(cursor.getString(cursor.getColumnIndex(LABEL_NAME)));
        track.setRelease(cursor.getString(cursor.getColumnIndex(RELEASE)));
        track.setTrack_type(cursor.getString(cursor.getColumnIndex(TRACK_TYPE)));
        track.setKey_signature(cursor.getString(cursor.getColumnIndex(KEY_SIGNATURE)));
        track.setIsrc(cursor.getString(cursor.getColumnIndex(ISRC)));
        track.setVideo_url(cursor.getString(cursor.getColumnIndex(VIDEO_URL)));
        track.setBpm(cursor.getString(cursor.getColumnIndex(BPM)));
        track.setRelease_year(cursor.getString(cursor.getColumnIndex(RELEASE_YEAR)));
        track.setRelease_month(cursor.getString(cursor.getColumnIndex(RELEASE_MONTH)));
        track.setRelease_day(cursor.getString(cursor.getColumnIndex(RELEASE_DAY)));
        track.setOriginal_format(cursor.getString(cursor.getColumnIndex(ORIGINAL_FORMAT)));
        track.setLicense(cursor.getString(cursor.getColumnIndex(LICENSE)));
        track.setUri(cursor.getString(cursor.getColumnIndex(URI)));
        track.setAttachments_uri(cursor.getString(cursor.getColumnIndex(ATTACHMENTS_URI)));
        track.setPermalink_url(cursor.getString(cursor.getColumnIndex(PERMALINK_URL)));
        track.setArtwork_url(cursor.getString(cursor.getColumnIndex(ARTWORK_URL)));
        track.setWaveform_url(cursor.getString(cursor.getColumnIndex(WAVEFORM_URL)));
        track.setStream_url(cursor.getString(cursor.getColumnIndex(STREAM_URL)));
        track.setDownload_url(cursor.getString(cursor.getColumnIndex(DOWNLOAD_URL)));
        track.setPlayback_count(cursor.getInt(cursor.getColumnIndex(PLAYBACK_COUNT)));
        track.setDownload_count(cursor.getInt(cursor.getColumnIndex(DOWNLOAD_COUNT)));
        track.setFavoritings_count(cursor.getInt(cursor.getColumnIndex(FAVORITINGS_COUNT)));
        track.setComment_count(cursor.getInt(cursor.getColumnIndex(COMMENT_COUNT)));

        Track.UserBean user = new Gson().fromJson(cursor.getString(cursor.getColumnIndex(USER)), Track.UserBean.class);
        track.setUser(user);

        return track;
    }


    public boolean addAllTracks(List<Track> trackList) {
        return saveAll(this, TABLE_NAME, trackList);
    }


    public long getDataCount() {
        return count(TABLE_NAME);
    }


    public LinkedList<Track> getAllTracks() {
        return (LinkedList<Track>) load(this, "SELECT * FROM " + TABLE_NAME);
    }


    public LinkedList<Track> getFilteredTracks(String key, String value, int limit) {
        return (LinkedList<Track>) load(this, "SELECT * FROM " + TABLE_NAME + " WHERE " + key + "='" + value + "' LIMIT " + limit);
    }


    public LinkedList<Track> getOrderedTracks(String orderBy, int limit) {
        return (LinkedList<Track>) load(this, "SELECT * FROM " + TABLE_NAME + " ORDER BY " + orderBy + " DESC LIMIT " + limit);
    }


    public LinkedList<Track> getRawDataForGenre() {
        return (LinkedList<Track>) load(this, "SELECT * FROM " + TABLE_NAME + " WHERE " + GENRE + "!= '' GROUP BY " + GENRE + " ORDER BY COUNT(*) DESC");
    }
}
