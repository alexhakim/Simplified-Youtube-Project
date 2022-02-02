package com.alexandrehakim.youtubesimplified.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alexandrehakim.youtubesimplified.R;
import com.alexandrehakim.youtubesimplified.databinding.FragmentHomeBinding;
import com.alexandrehakim.youtubesimplified.ui.search.TinyDB;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RequestQueue requestQueue;
    TinyDB tinyDB;

    ImageView video1ImageView, video2ImageView, video3ImageView, video4ImageView, video5ImageView;
    TextView video1TextView, video2TextView, video3TextView, video4TextView, video5TextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tinyDB = new TinyDB(getContext());

        bindings();

        // https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=relevance&chart=mostPopular&regionCode=CA&type=video&videoDefinition=high&key=AIzaSyA_LuldauHIe-yH81W9oVDcdVpvdPH7rTo

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=relevance&chart=mostPopular&regionCode=CA&type=video&videoDefinition=high&key=AIzaSyA_LuldauHIe-yH81W9oVDcdVpvdPH7rTo";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("items");

                    JSONObject item1 = jsonArray.getJSONObject(0);
                    JSONObject item2 = jsonArray.getJSONObject(1);
                    JSONObject item3 = jsonArray.getJSONObject(2);
                    JSONObject item4 = jsonArray.getJSONObject(3);
                    JSONObject item5 = jsonArray.getJSONObject(4);

                    getItem1(item1);
                    getItem2(item2);
                    getItem3(item3);
                    getItem4(item4);
                    getItem5(item5);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });

        requestQueue.add(request);

        return root;

    }
    private void getItem1(JSONObject item){
        // get video ID
        try {
            JSONObject itemid = item.getJSONObject("id");
            String item0VideoID = itemid.getString("videoId");
            tinyDB.putString("pos0",item0VideoID);

            // get video title
            JSONObject itemsnippet = item.getJSONObject("snippet");
            String itemTitle = itemsnippet.getString("title");
            video1TextView.setText(itemTitle);

            // get video thumbnail
            JSONObject itemthumbnails = itemsnippet.getJSONObject("thumbnails");
            JSONObject itemmedium = itemthumbnails.getJSONObject("high");
            String itemThumbnail = itemmedium.getString("url");
            Picasso.get().load(itemThumbnail).into(video1ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem2(JSONObject item){
        try {
            // get video ID
            JSONObject itemid = item.getJSONObject("id");
            String itemVideoID = itemid.getString("videoId");
            tinyDB.putString("pos1",itemVideoID);

            // get video title
            JSONObject itemsnippet = item.getJSONObject("snippet");
            String itemTitle = itemsnippet.getString("title");
            video2TextView.setText(itemTitle);

            // get video thumbnail
            JSONObject itemthumbnails = itemsnippet.getJSONObject("thumbnails");
            JSONObject itemmedium = itemthumbnails.getJSONObject("high");
            String itemThumbnail = itemmedium.getString("url");
            Picasso.get().load(itemThumbnail).into(video2ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem3(JSONObject item){
        try {
            // get video ID
            JSONObject itemid = item.getJSONObject("id");
            String itemVideoID = itemid.getString("videoId");
            tinyDB.putString("pos2",itemVideoID);

            // get video title
            JSONObject itemsnippet = item.getJSONObject("snippet");
            String itemTitle = itemsnippet.getString("title");
            video3TextView.setText(itemTitle);

            // get video thumbnail
            JSONObject itemthumbnails = itemsnippet.getJSONObject("thumbnails");
            JSONObject itemmedium = itemthumbnails.getJSONObject("high");
            String itemThumbnail = itemmedium.getString("url");
            Picasso.get().load(itemThumbnail).into(video3ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem4(JSONObject item){
        try {
            // get video ID
            JSONObject itemid = item.getJSONObject("id");
            String itemVideoID = itemid.getString("videoId");
            tinyDB.putString("pos3",itemVideoID);

            // get video title
            JSONObject itemsnippet = item.getJSONObject("snippet");
            String itemTitle = itemsnippet.getString("title");
            video4TextView.setText(itemTitle);

            // get video thumbnail
            JSONObject itemthumbnails = itemsnippet.getJSONObject("thumbnails");
            JSONObject itemmedium = itemthumbnails.getJSONObject("high");
            String itemThumbnail = itemmedium.getString("url");
            Picasso.get().load(itemThumbnail).into(video4ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem5(JSONObject item){
        try {
            // get video ID
            JSONObject itemid = item.getJSONObject("id");
            String itemVideoID = itemid.getString("videoId");
            tinyDB.putString("pos4",itemVideoID);

            // get video title
            JSONObject itemsnippet = item.getJSONObject("snippet");
            String itemTitle = itemsnippet.getString("title");
            video5TextView.setText(itemTitle);

            // get video thumbnail
            JSONObject itemthumbnails = itemsnippet.getJSONObject("thumbnails");
            JSONObject itemmedium = itemthumbnails.getJSONObject("high");
            String itemThumbnail = itemmedium.getString("url");
            Picasso.get().load(itemThumbnail).into(video5ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void bindings(){
        video1ImageView = binding.video1ImageView;
        video2ImageView = binding.video2ImageView;
        video3ImageView = binding.video3ImageView;
        video4ImageView = binding.video4ImageView;
        video5ImageView = binding.video5ImageView;
        video1TextView = binding.video1TextView;
        video2TextView = binding.video2TextView;
        video3TextView = binding.video3TextView;
        video4TextView = binding.video4TextView;
        video5TextView = binding.video5TextView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}