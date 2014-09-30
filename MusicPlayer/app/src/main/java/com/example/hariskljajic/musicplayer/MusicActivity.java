package com.example.hariskljajic.musicplayer;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


public class MusicActivity extends ListActivity implements View.OnClickListener{

    public final static String TRACK = "track";
    public final static String ACTION = "action";

    private int currentTrack = 0;
    private boolean isPlaying = false;

    // Const Variables
    final static int PLAY = 1;
    final static int PAUSE = 2;
    final static int PLAY_SONG = 3;

    ImageButton playBtn;
    ImageButton nextBtn;
    ImageButton prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        if(checkIfStorageAvailable()){
            Log.d("MusicActivity", "Ja, den är tillgänglig!");

            ArrayList<Track> trackList = getPlaylist();

            ArrayAdapter<Track> musicAdapter = new ArrayAdapter<Track>(this, android.R.layout.simple_list_item_1, trackList);

            setListAdapter(musicAdapter);

            playBtn = (ImageButton)findViewById(R.id.playBtn);
            nextBtn = (ImageButton)findViewById(R.id.nextBtn);
            prevBtn = (ImageButton)findViewById(R.id.prevBtn);

            playBtn.setOnClickListener(this);
            nextBtn.setOnClickListener(this);
            prevBtn.setOnClickListener(this);
        }

    }

    private ArrayList<Track> getPlaylist() {
        Cursor musicResult = getContentResolver().query(
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
        }
        Log.d("MusicActivity", "" + musicResult.getCount());
        musicResult.close();

        return trackList;
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intentService = new Intent(this, MusicService.class);
        currentTrack = position;
        intentService.putExtra(TRACK, currentTrack).putExtra(ACTION, PLAY_SONG);
        startService(intentService);

        playBtn.setImageResource(R.drawable.ic_action_pause);
        isPlaying = true;
    }



    private boolean checkIfStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intentService = new Intent(this, MusicService.class);
        if(view.getId() == R.id.playBtn){
            if(isPlaying){
                intentService.putExtra(ACTION, PAUSE);
                playBtn.setImageResource(R.drawable.ic_action_play);
                isPlaying = false;
            }else {
                intentService.putExtra(ACTION, PLAY_SONG);
                playBtn.setImageResource(R.drawable.ic_action_pause);
                isPlaying = true;
            }
        }
        else if(view.getId() == R.id.nextBtn){
            currentTrack++;
            intentService.putExtra(TRACK, currentTrack);
            intentService.putExtra(ACTION, PLAY_SONG);
        }
        else if(view.getId() == R.id.prevBtn){
            currentTrack--;
            intentService.putExtra(TRACK, currentTrack);
            intentService.putExtra(ACTION, PLAY_SONG);
        }
        startService(intentService);
    }
}
