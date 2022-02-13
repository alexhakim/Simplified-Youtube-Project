package com.alexandrehakim.youtubesimplified.ui.library;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.alexandrehakim.youtubesimplified.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CustomHistoryListAdapter extends ArrayAdapter {

    private Activity context;
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> images = new ArrayList<String>();

    // constructor
    public CustomHistoryListAdapter(Activity context, ArrayList<String> titles, ArrayList<String> images){
        super(context, R.layout.layout_adapterview, titles);
        this.context = context;
        this.titles = titles;
        this.images = images;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent){
        View r = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView==null){
            r = inflater.inflate(R.layout.layout_adapterview, null, true);
        }

        // bindings
        TextView titleTextView = (TextView) r.findViewById(R.id.titleTextView);
        ImageView videoImageView = (ImageView) r.findViewById(R.id.videoImageView);

        // set
        titleTextView.setText(titles.get(i));
        Picasso.get().load(images.get(i)).into(videoImageView);

        return r;
    }
}
