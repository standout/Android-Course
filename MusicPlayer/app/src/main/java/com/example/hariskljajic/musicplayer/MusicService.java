package com.example.hariskljajic.musicplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.hariskljajic.musicplayer.Activites.MusicActivity;
import com.example.hariskljajic.musicplayer.Models.Track;
import com.example.hariskljajic.musicplayer.Models.TrackListHelper;

import java.util.ArrayList;

/**
 * Created by Haris Kljajic on 2014-09-25.
 */
public class MusicService extends Service{

    public final static String TRACK = "track";
    public final static String ACTION = "action";
    private MediaPlayer mediaPlayer;
    private Track currentTrack;

    private ArrayList<Track> trackList;
    private boolean isStarted = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MusicService", "Creating service...");
        mediaPlayer = new MediaPlayer();

        TrackListHelper trackListHelper = new TrackListHelper(this);
        if(trackListHelper.checkIfStorageAvailable()){
            trackList = trackListHelper.get();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MusicService", "Service started...");

        int track = intent.getIntExtra(TRACK, 0);
        int action = intent.getIntExtra(ACTION, 1);

        switch (action) {
            case TrackListHelper.PLAY:
                mediaPlayer.start();
                break;
            case TrackListHelper.PAUSE:
                mediaPlayer.pause();
                break;
            case TrackListHelper.PLAY_SONG:
                musicPlayer(trackList.get(track));
                break;
            default:
                break;
        }
        if(!isStarted)
            ongoingNotification();

        return START_STICKY;
    }

    private void ongoingNotification() {
        final int myID = 1234;

        //The intent to launch when the user clicks the expanded notification
        Intent intent = new Intent(this, MusicActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //This constructor is deprecated. Use Notification.Builder instead
        Notification notice = new Notification(R.drawable.ic_launcher, currentTrack.getName().split(".mp3")[0] + " - " + currentTrack.getArtist(), 3);

        //This method is deprecated. Use Notification.Builder instead.
        notice.setLatestEventInfo(this, "Spinkify", "Click to open", pendIntent);

        notice.flags |= Notification.FLAG_NO_CLEAR;

        startForeground(myID, notice);

        isStarted = true;
    }

    private void musicPlayer(Track track){
        if (track == null)
            return;
        try {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop(); // Stop current song.

            mediaPlayer.reset(); // reset resource of player
            mediaPlayer.setDataSource(this, Uri.parse(track.getPath())); // set Song to play
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION); // select audio stream
            mediaPlayer.prepare(); // prepare resource
            // on completion handler
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                    // onDone
            {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    musicPlayer(currentTrack.getNext());
                }
            });
            mediaPlayer.start(); // play!
            currentTrack = track;
        } catch (Exception e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            Log.d("MusicService error", e.toString());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MusicService.class.toString(), "Död!");
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
