package com.rhythmdb;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Entry {

    @Attribute(name = "type")
    private String type;

    @Element(name = "title", required = false)
    private String title = "";

    @Element(name = "genre", required = false)
    private String genre = "";

    @Element(name = "artist", required = false)
    private String artist = "";

    @Element(name = "album", required = false)
    private String album = "";

    @Element(name = "track-number", required = false)
    private Integer trackNumber; //6

    @Element(name = "track-total", required = false)
    private Integer trackTotal; //10

    @Element(name = "disc-number", required = false)
    private Integer discNumber; //10

    @Element(name = "disc-total", required = false)
    private Integer discTotal; //10

    @Element(name = "duration", required = false)
    private Integer duration; //330

    @Element(name = "file-size", required = false)
    private Integer fileSize; //5281796

    @Element(name = "location")
    private String location; //file:///home/ale/Music/Los%20Piojos/Ay%20ay%20ay/Ando%20ganas%20(llora%20llora).mp3

    @Element(name = "mtime", required = false)
    private Integer mtime; //1414775700

    @Element(name = "first-seen", required = false)
    private Integer firstSeen;//1470593473

    @Element(name = "last-seen", required = false)
    private Integer lastSeen; //1471130542

    @Element(name = "play-count", required = false)
    private Integer playCount; //10

    @Element(name = "last-played", required = false)
    private Long lastPlayed; //10

    @Element(name = "bitrate", required = false)
    private Integer bitrate; //127

    @Element(name = "date", required = false)
    private Integer date; //727929

    @Element(name = "media-type")
    private String mediaType; //audio/mpeg

    @Element(name = "comment", required = false)
    private String comment; //10

    @Element(name = "album-artist", required = false)
    private String albumArtist; //Los Piojos

    @Element(name = "composer", required = false)
    private String composer; //Unknown



    protected Entry() {
    }

    public Entry(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMtime() {
        return mtime;
    }

    public void setMtime(Integer mtime) {
        this.mtime = mtime;
    }

    public Integer getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Integer firstSeen) {
        this.firstSeen = firstSeen;
    }

    public Integer getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Integer lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackTotal() {
        return trackTotal;
    }

    public void setTrackTotal(Integer trackTotal) {
        this.trackTotal = trackTotal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Long getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Long lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public Integer getDiscTotal() {
        return discTotal;
    }

    public void setDiscTotal(Integer discTotal) {
        this.discTotal = discTotal;
    }
}