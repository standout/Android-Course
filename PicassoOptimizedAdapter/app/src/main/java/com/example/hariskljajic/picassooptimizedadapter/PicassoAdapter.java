    package com.example.hariskljajic.picassooptimizedadapter;

    import android.content.Context;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.Gallery;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.squareup.picasso.Picasso;

    import java.util.ArrayList;
    import java.util.zip.Inflater;

    /**
     * Created by Haris Kljajic on 2014-09-12.
     */
    public class PicassoAdapter extends ArrayAdapter<GalleryFrame> {

        private Context context;
        private ArrayList<GalleryFrame> galleryFrameArrayList;

        private int count;
        private int count2;

        public PicassoAdapter(Context context, ArrayList<GalleryFrame> galleryFrameArrayList) {
            super(context, R.layout.row_gallery_frame, galleryFrameArrayList);

            this.context = context;
            this.galleryFrameArrayList = galleryFrameArrayList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        {
                ViewHolder viewHolder;

                if(convertView == null){
                    count++;
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.row_gallery_frame, parent, false);

                    viewHolder = new ViewHolder();

                    viewHolder.nameHolder = (TextView) convertView.findViewById(R.id.nameTv);
                    viewHolder.imageHolder = (ImageView) convertView.findViewById(R.id.urlIv);

                    convertView.setTag(viewHolder);
                }
                else{
                    count2++;
                    viewHolder = (ViewHolder)convertView.getTag();
                }

                GalleryFrame galleryFrame = galleryFrameArrayList.get(position);

                viewHolder.nameHolder.setText(galleryFrame.getName());
                Picasso.with(context).load(galleryFrame.getUrl()).into(viewHolder.imageHolder);

                Log.d("PicassoAdapter", String.valueOf(count));
                Log.d("PicassoAdapter Count 2", String.valueOf(count2));

                return convertView;
            }
        }

        public static class ViewHolder{
            public TextView nameHolder;
            public ImageView imageHolder;
        }
    }

