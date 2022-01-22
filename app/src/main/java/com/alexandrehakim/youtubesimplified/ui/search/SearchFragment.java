package com.alexandrehakim.youtubesimplified.ui.search;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private RequestQueue requestQueue;
    ArrayList<String> IDs = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();
    String ID, ttl;

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

        searchEditText = binding.searchEditText;
        searchButton = binding.searchButton;
        item0TextView = binding.item0TextView;
        item0ImageView = binding.item0ImageView;

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString(); // input by user
                String URL = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=relevance&q=" + query + "&type=video&videoDefinition=high&key=AIzaSyA_LuldauHIe-yH81W9oVDcdVpvdPH7rTo";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");

                            JSONObject item0 = jsonArray.getJSONObject(0);
                            JSONObject item1 = jsonArray.getJSONObject(1);
                            JSONObject item2 = jsonArray.getJSONObject(2);
                            JSONObject item3 = jsonArray.getJSONObject(3);
                            JSONObject item4 = jsonArray.getJSONObject(4);

                            Log.i("item0",String.valueOf(item0));
                            Log.i("item1",String.valueOf(item1));
                            Log.i("item2",String.valueOf(item2));
                            Log.i("item3",String.valueOf(item3));
                            Log.i("item4",String.valueOf(item4));

                            // temp


                            String titleSegment = item0.getString("snippet");
                            String[] trim = titleSegment.split("\"");
                            String title = trim[11];
                            Log.i("VIDEOTITLE",title);
                            titles.add(title);
                            ttl=title;

                            String poster = trim[25];
                            poster = poster.replaceAll("\\\\","");
                            Log.i("POSTER",poster);
                            //Toast.makeText(getContext(), poster, Toast.LENGTH_SHORT).show();
                            Picasso.get().load(poster).into(item0ImageView);
                            // hashmap instead??
                            Log.i("songTitles",String.valueOf(titles));
                            Log.i("songIds",String.valueOf(IDs));



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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getJSONData(JSONObject item){
        try {
            String titleSegment = item.getString("snippet");
            String[] trim = titleSegment.split("\"");
            String title = trim[11];
            Log.i("VIDEOTITLE", title);
            titles.add(title);
            ttl = title;

            String poster = trim[25];
            poster = poster.replaceAll("\\\\", "");
            Log.i("POSTER", poster);
            //Toast.makeText(getContext(), poster, Toast.LENGTH_SHORT).show();
            Picasso.get().load(poster).into(item0ImageView);
            // hashmap instead??
            Log.i("songTitles", String.valueOf(titles));
            Log.i("songIds", String.valueOf(IDs));

            item0TextView.setText(title);
            Log.i("item0text",item0TextView.getText().toString());

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}