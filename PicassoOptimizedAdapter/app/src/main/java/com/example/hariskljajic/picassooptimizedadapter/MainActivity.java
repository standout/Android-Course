package com.example.hariskljajic.picassooptimizedadapter;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    ArrayList<GalleryFrame> galleryFrameArrayList;
    PicassoAdapter picassoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleryFrameArrayList = new ArrayList<GalleryFrame>();
        galleryFrameArrayList.add(new GalleryFrame("http://standout.se/img/david.elbe.jpg", "David Elbe", "balblallalsldlasdlalsdkalsd"));
        galleryFrameArrayList.add(new GalleryFrame("http://standout.se/img/david.elbe.jpg", "David Elbe", "balblallalsldlasdlalsdkalsd"));
        galleryFrameArrayList.add(new GalleryFrame("http://standout.se/img/david.elbe.jpg", "David Elbe", "balblallalsldlasdlalsdkalsd"));
        galleryFrameArrayList.add(new GalleryFrame("http://standout.se/img/david.elbe.jpg", "David Elbe", "balblallalsldlasdlalsdkalsd"));
        galleryFrameArrayList.add(new GalleryFrame("http://standout.se/img/david.elbe.jpg", "David Elbe", "balblallalsldlasdlalsdkalsd"));

        picassoAdapter = new PicassoAdapter(this, galleryFrameArrayList);

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
}
