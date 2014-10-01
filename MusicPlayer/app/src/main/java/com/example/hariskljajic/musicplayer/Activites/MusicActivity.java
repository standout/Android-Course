package com.example.hariskljajic.musicplayer.Activites;

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

import com.example.hariskljajic.musicplayer.Models.TrackListHelper;
import com.example.hariskljajic.musicplayer.MusicService;
import com.example.hariskljajic.musicplayer.R;
import com.example.hariskljajic.musicplayer.Models.Track;

import java.util.ArrayList;


public class MusicActivity extends ListActivity implements View.OnClickListener{

    public final static String TRACK = "track";
    public final static String ACTION = "action";

    private int currentTrack = 0;
    private boolean isPlaying = false;

    ImageButton playBtn;
    ImageButton nextBtn;
    ImageButton prevBtn;
    ImageButton stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        TrackListHelper trackListHelper = new TrackListHelper(this);
        if(trackListHelper.checkIfStorageAvailable()){
            Log.d("MusicActivity", "Ja, den är tillgänglig!");

            ArrayList<Track> trackList = trackListHelper.get();

            ArrayAdapter<Track> musicAdapter = new ArrayAdapter<Track>(this, android.R.layout.simple_list_item_1, trackList);

            setListAdapter(musicAdapter);

            playBtn = (ImageButton)findViewById(R.id.playBtn);
            nextBtn = (ImageButton)findViewById(R.id.nextBtn);
            prevBtn = (ImageButton)findViewById(R.id.prevBtn);
            stopBtn = (ImageButton)findViewById(R.id.stopBtn);

            playBtn.setOnClickListener(this);
            nextBtn.setOnClickListener(this);
            prevBtn.setOnClickListener(this);
            stopBtn.setOnClickListener(this);
        }

    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intentService = new Intent(this, MusicService.class);
        currentTrack = position;
        intentService.putExtra(TRACK, currentTrack).putExtra(ACTION, TrackListHelper.PLAY_SONG);
        startService(intentService);

        playBtn.setImageResource(R.drawable.ic_action_pause);
        isPlaying = true;
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
                intentService.putExtra(ACTION, TrackListHelper.PAUSE);
                playBtn.setImageResource(R.drawable.ic_action_play);
                isPlaying = false;
                startService(intentService);
            }else {
                intentService.putExtra(ACTION, TrackListHelper.PLAY_SONG);
                playBtn.setImageResource(R.drawable.ic_action_pause);
                isPlaying = true;
                startService(intentService);
            }
        }
        else if(view.getId() == R.id.nextBtn){
            currentTrack++;
            intentService.putExtra(TRACK, currentTrack);
            intentService.putExtra(ACTION, TrackListHelper.PLAY_SONG);
            startService(intentService);
        }
        else if(view.getId() == R.id.prevBtn){
            currentTrack--;
            intentService.putExtra(TRACK, currentTrack);
            intentService.putExtra(ACTION, TrackListHelper.PLAY_SONG);
            startService(intentService);
        }
        else if(view.getId() == R.id.stopBtn){
            stopService(new Intent(this, MusicService.class));
            playBtn.setImageResource(R.drawable.ic_action_play);
            isPlaying = false;
        }

    }
}
