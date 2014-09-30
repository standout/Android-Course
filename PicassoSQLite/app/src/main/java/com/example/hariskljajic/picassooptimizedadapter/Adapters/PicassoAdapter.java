package com.example.hariskljajic.picassooptimizedadapter.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hariskljajic.picassooptimizedadapter.Data.Contracts.ContractGallery;
import com.example.hariskljajic.picassooptimizedadapter.R;
import com.squareup.picasso.Picasso;

import static com.example.hariskljajic.picassooptimizedadapter.Data.Contracts.ContractGallery.*;

/**
 * Created by Haris Kljajic on 2014-09-12.
 */
public class PicassoAdapter extends CursorAdapter {

    LayoutInflater inflater;
    ViewHolder viewHolder;
    int count;

    public PicassoAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        count++;
        viewHolder = new ViewHolder();

        View view = inflater.inflate(R.layout.row_gallery_frame, parent, false);
        viewHolder.urlHolder = (ImageView)view.findViewById(R.id.urlIv);
        viewHolder.nameHolder = (TextView)view.findViewById(R.id.nameTv);

        view.setTag(viewHolder);
        Log.d("NewInstance", count+"");
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        viewHolder = (ViewHolder)view.getTag();

        viewHolder.nameHolder.setText(cursor.getString((cursor.getColumnIndex(Gallery.COLUMN_NAME_NAME))));
        Picasso.with(context).load(cursor.getString(cursor.getColumnIndex(Gallery.COLUMN_NAME_URL))).placeholder(R.drawable.ic_launcher).resize(460, 325).into(viewHolder.urlHolder);
    }

     static class ViewHolder{
        public TextView nameHolder;
        public ImageView urlHolder;
    }
}

