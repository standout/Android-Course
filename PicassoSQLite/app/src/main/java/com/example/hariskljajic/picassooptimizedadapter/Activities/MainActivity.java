package com.example.hariskljajic.picassooptimizedadapter.Activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

        galleryDbHelper = new GalleryDbHelper(this);
        Cursor galleryCursor = galleryDbHelper.get();

        picassoAdapter = new PicassoAdapter(this, galleryCursor, false);
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
        GalleryFrame galleryFrame = new GalleryFrame("http://www.skk.se/PageFiles/3253/513-japansk-spets.jpg", "Haris", "Hejhejhejhejhej Hejhejhejhejhej Hejhejhejhejhej");
        galleryDbHelper.insert(galleryFrame);
    }
}
