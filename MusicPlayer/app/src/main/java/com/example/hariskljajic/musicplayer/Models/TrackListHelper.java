package com.example.hariskljajic.musicplayer.Models;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Haris Kljajic on 2014-10-01.
 */
public class TrackListHelper {

    private Context context;

    // Const variables
    public final static int PLAY = 1;
    public final static int PAUSE = 2;
    public final static int PLAY_SONG = 3;

    public final static String TRACK = "track";
    public final static String ACTION = "action";

    // Total number of tracks in playlist.
    public static int numberOfTracks;

    // Constructor injecting a context to this helper.
    public TrackListHelper(Context context){
        this.context = context;
    }

    // Querying all music from Audio media folder and converting it from cursor to ArrayList<Track>
    public ArrayList<Track> get(){
        Cursor musicResult = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] {
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DATA },
                MediaStore.Audio.Media.IS_MUSIC + " > 0 ",
                null,
                null
        );

        ArrayList<Track> trackList = new ArrayList<Track>();

        if (musicResult.getCount() > 0) {
            musicResult.moveToFirst();
            Track prev = null;
            do {
                Track track = new Track(
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.DATA))
                );

                if (prev != null) //here prev song linked to current one. To simple play them in list
                    prev.setNext(track);

                prev = track;
                trackList.add(track);
            }
            while (musicResult.moveToNext());

            prev.setNext(trackList.get(0)); //play in cycle;

            numberOfTracks = musicResult.getCount();
        }
        Log.d("MusicActivity", "" + musicResult.getCount());

        musicResult.close();

        return trackList;
    }

    // Method used to check if Storage is available before starting using it.
    public boolean checkIfStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
