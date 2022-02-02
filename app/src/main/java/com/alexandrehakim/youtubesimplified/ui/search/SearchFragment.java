package com.alexandrehakim.youtubesimplified.ui.search;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alexandrehakim.youtubesimplified.PlayVideoActivity;
import com.alexandrehakim.youtubesimplified.databinding.FragmentLibraryBinding;
import com.alexandrehakim.youtubesimplified.databinding.FragmentSearchBinding;
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

import java.util.ArrayList;
import java.util.Arrays;


public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private RequestQueue requestQueue;
    TinyDB tinyDB;

    EditText searchEditText;
    Button searchButton;
    TextView item0TextView,item1TextView,item2TextView,item3TextView,item4TextView;
    ImageView item0ImageView,item1ImageView,item2ImageView,item3ImageView,item4ImageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        Intent playVideoIntent = new Intent(getContext(), PlayVideoActivity.class);
        tinyDB = new TinyDB(getContext());

        searchEditText = binding.searchEditText;
        searchButton = binding.searchButton;
        item0TextView = binding.item0TextView;
        item0ImageView = binding.item0ImageView;
        item1TextView = binding.item1TextView;
        item1ImageView = binding.item1ImageView;
        item2TextView = binding.item2TextView;
        item2ImageView = binding.item2ImageView;
        item3TextView = binding.item3TextView;
        item3ImageView = binding.item3ImageView;
        item4TextView = binding.item4TextView;
        item4ImageView = binding.item4ImageView;


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString(); // input by user
                String url = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=relevance&q=" + query + "&type=video&videoDefinition=high&key=https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=relevance&chart=mostPopular&regionCode=FR&type=video&videoDefinition=high&key=AIzaSyApDn2g2N5FrgX24CMSx08QSn0asjGvWws";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            // hide soft keyboard after search
                            InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);


                            JSONArray jsonArray = response.getJSONArray("items");

                            JSONObject item0 = jsonArray.getJSONObject(0);
                            JSONObject item1 = jsonArray.getJSONObject(1);
                            JSONObject item2 = jsonArray.getJSONObject(2);
                            JSONObject item3 = jsonArray.getJSONObject(3);
                            JSONObject item4 = jsonArray.getJSONObject(4);

                            Log.d("item0",String.valueOf(item0));

                            getItem0(item0);
                            getItem1(item1);
                            getItem2(item2);
                            getItem3(item3);
                            getItem4(item4);


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
            }
        });

        item0ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos0");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item0TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos0");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos1");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos1");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos2");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos2");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos3");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos3");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos4");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        item4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = tinyDB.getString("pos4");
                playVideoIntent.putExtra("pos",videoID);
                startActivity(playVideoIntent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getItem0(JSONObject item0){
        // get video ID
        try {
            JSONObject item0id = item0.getJSONObject("id");
            Log.d("item0id", String.valueOf(item0id));
            String item0VideoID = item0id.getString("videoId");
            Log.d("item0VideoID", item0VideoID);
            tinyDB.putString("pos0",item0VideoID);

            // get video title
            JSONObject item0snippet = item0.getJSONObject("snippet");
            Log.d("item0snippet", String.valueOf(item0snippet));
            String item0Title = item0snippet.getString("title");
            Log.d("titleString", item0Title);
            item0TextView.setText(item0Title);

            // get video thumbnail
            JSONObject item0thumbnails = item0snippet.getJSONObject("thumbnails");
            Log.d("item0thumbnails", String.valueOf(item0thumbnails));
            JSONObject item0medium = item0thumbnails.getJSONObject("medium");
            Log.d("item0medium", String.valueOf(item0medium));
            String item0Thumbnail = item0medium.getString("url");
            Log.d("item0Thumbnail", item0Thumbnail);
            Picasso.get().load(item0Thumbnail).into(item0ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem1(JSONObject item1){
        try {
            // get video ID
            JSONObject item1id = item1.getJSONObject("id");
            String item1VideoID = item1id.getString("videoId");
            tinyDB.putString("pos1",item1VideoID);

            // get video title
            JSONObject item1snippet = item1.getJSONObject("snippet");
            String item1Title = item1snippet.getString("title");
            item1TextView.setText(item1Title);

            // get video thumbnail
            JSONObject item1thumbnails = item1snippet.getJSONObject("thumbnails");
            JSONObject item1medium = item1thumbnails.getJSONObject("medium");
            String item1Thumbnail = item1medium.getString("url");
            Picasso.get().load(item1Thumbnail).into(item1ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem2(JSONObject item2){
        try {
            // get video ID
            JSONObject item2id = item2.getJSONObject("id");
            String item2VideoID = item2id.getString("videoId");
            tinyDB.putString("pos2",item2VideoID);

            // get video title
            JSONObject item2snippet = item2.getJSONObject("snippet");
            String item2Title = item2snippet.getString("title");
            item2TextView.setText(item2Title);

            // get video thumbnail
            JSONObject item2thumbnails = item2snippet.getJSONObject("thumbnails");
            JSONObject item2medium = item2thumbnails.getJSONObject("medium");
            String item2Thumbnail = item2medium.getString("url");
            Picasso.get().load(item2Thumbnail).into(item2ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem3(JSONObject item3){
        try {
            // get video ID
            JSONObject item3id = item3.getJSONObject("id");
            String item3VideoID = item3id.getString("videoId");
            tinyDB.putString("pos3",item3VideoID);

            // get video title
            JSONObject item3snippet = item3.getJSONObject("snippet");
            String item3Title = item3snippet.getString("title");
            item3TextView.setText(item3Title);

            // get video thumbnail
            JSONObject item3thumbnails = item3snippet.getJSONObject("thumbnails");
            JSONObject item3medium = item3thumbnails.getJSONObject("medium");
            String item3Thumbnail = item3medium.getString("url");
            Picasso.get().load(item3Thumbnail).into(item3ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getItem4(JSONObject item4){
        try {
            // get video ID
            JSONObject item4id = item4.getJSONObject("id");
            String item4VideoID = item4id.getString("videoId");
            tinyDB.putString("pos4",item4VideoID);

            // get video title
            JSONObject item4snippet = item4.getJSONObject("snippet");
            String item4Title = item4snippet.getString("title");
            item4TextView.setText(item4Title);

            // get video thumbnail
            JSONObject item4thumbnails = item4snippet.getJSONObject("thumbnails");
            JSONObject item4medium = item4thumbnails.getJSONObject("medium");
            String item4Thumbnail = item4medium.getString("url");
            Picasso.get().load(item4Thumbnail).into(item4ImageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}