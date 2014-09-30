package com.example.hariskljajic.musicplayer;

/**
 * Created by Haris Kljajic on 2014-09-25.
 */
public class Track {

    private String name;
    private String artist;
    private String album;
    private String path;
    private Track nextTrack = null;

    public Track(String name, String artist, String album, String path){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNext(Track track)
    {
        nextTrack = track;
    }

    public Track getNext()
    {
        return nextTrack;
    }

    public String toString(){
        return name.split(".mp3")[0];
    }

}
