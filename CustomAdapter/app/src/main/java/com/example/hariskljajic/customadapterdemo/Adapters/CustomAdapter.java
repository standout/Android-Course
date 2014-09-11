package com.example.hariskljajic.customadapterdemo.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hariskljajic.customadapterdemo.R;

import java.util.ArrayList;

/**
 * Created by Haris Kljajic on 2014-09-11.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private final Context _context;
    private final ArrayList<String> _contactList;

    private int counter;

    public CustomAdapter(Context context, ArrayList contactList) {
        super(context, R.layout.row_names, contactList);

        this._context = context;
        this._contactList = contactList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        counter++;
        // Create inflater
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Get rowView from inflater
        convertView = inflater.inflate(R.layout.row_names, parent, false);

        TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv);
        ImageView imageIv = (ImageView) convertView.findViewById(R.id.imageIv);
        nameTv.setText(_contactList.get(position));
        imageIv.setImageResource(R.drawable.ic_launcher);


        return convertView;
    }
}
