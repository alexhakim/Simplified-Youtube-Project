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
import java.util.Arrays;


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
                            String poster = trim[25];
                            poster = poster.replaceAll("\\\\","");
                            Log.i("POSTER",poster);
                            //Toast.makeText(getContext(), poster, Toast.LENGTH_SHORT).show();
                            Picasso.get().load(poster).into(item0ImageView);
                            // hashmap instead??
                            item0TextView.setText(title);

                            String titleSegment2 = item1.getString("snippet");
                            String[] trim2 = titleSegment2.split("\"");
                            String title2 = trim2[11];
                            String poster2 = trim[25];
                            Log.d("TITLE2",title2);
                            item1TextView.setText(title2);
                            poster2 = poster2.replaceAll("\\\\","");
                            Log.d("POSTER2",poster2);
                            try {
                                Picasso.get().load(poster2).into(item1ImageView);
                            }catch (IllegalArgumentException e){
                                e.printStackTrace();
                            }


                            String titleSegment3 = item2.getString("snippet");
                            String[] trim3 = titleSegment3.split("\"");
                            String title3 = trim3[11];
                            JSONObject snippet = item2.getJSONObject("snippet");
                            Log.i("SNIPPET", String.valueOf(snippet));
                            JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                            Log.i("thumbnails", String.valueOf(thumbnails));
                            String[] parseURL = String.valueOf(thumbnails).split("https");
                            String poster3Temp = parseURL[1];
                            String[] finalParse = poster3Temp.split(",\"width");
                            String poster3 = "https" + finalParse[0];
                            poster3 = poster3.replaceAll("\\\\","");
                            Log.d("posterString",poster3);
                            Picasso.get().load(poster3).into(item2ImageView);
                            item2TextView.setText(title3);

                            String titleSegment4 = item3.getString("snippet");
                            String[] trim4 = titleSegment4.split("\"");
                            String title4 = trim4[11];
                            String poster4 = trim4[25];

                            Log.d("tempposter4",poster4);
                            poster4 = poster4.replaceAll("\\\\","");
                            try {
                                Picasso.get().load(poster4).into(item3ImageView);
                            }catch (IllegalArgumentException e){
                                e.printStackTrace();
                            }
                            item3TextView.setText(title4);

                            String titleSegment5 = item4.getString("snippet");
                            String[] trim5 = titleSegment5.split("\"");
                            String title5 = trim5[11];
                            String poster5 = trim5[25];
                            poster5 = poster5.replaceAll("\\\\","");
                            try {
                                Picasso.get().load(poster5).into(item4ImageView);
                            }catch (IllegalArgumentException e){
                                e.printStackTrace();
                            }
                            item4TextView.setText(title5);


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
}