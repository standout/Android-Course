package com.example.hariskljajic.picassooptimizedadapter.Activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import com.example.hariskljajic.picassooptimizedadapter.Data.GalleryDbHelper;
import com.example.hariskljajic.picassooptimizedadapter.Models.GalleryFrame;
import com.example.hariskljajic.picassooptimizedadapter.Adapters.PicassoAdapter;
import com.example.hariskljajic.picassooptimizedadapter.R;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    ArrayList<GalleryFrame> galleryFrameArrayList;
    PicassoAdapter picassoAdapter;
    GalleryDbHelper galleryDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of Gallery Database
        galleryDbHelper = new GalleryDbHelper(this);

        // Get latest data
        Cursor galleryCursor = galleryDbHelper.get();

        // Create an instance of adapter, pass context and galleryCursor from database
        picassoAdapter = new PicassoAdapter(this, galleryCursor, false);

        // Set the adapter on the listview
        setListAdapter(picassoAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void addGalleryFrame(View view) {

        // Create a new object of GalleryFrame
        GalleryFrame galleryFrame = new GalleryFrame("http://blogg.vk.se/wp-content/uploads/oldblog/316/images/juli2040annie.jpg", "Haris", "Hejhejhejhejhej Hejhejhejhejhej Hejhejhejhejhej");

        // Insert galleryFrame object
        galleryDbHelper.insert(galleryFrame);

        // Get latest data
        Cursor galleryCursor = galleryDbHelper.get();
        // Set new cursor data on the adapter.
        picassoAdapter.changeCursor(galleryCursor);

        // Notify data set changed
        picassoAdapter.notifyDataSetChanged();
    }
}
