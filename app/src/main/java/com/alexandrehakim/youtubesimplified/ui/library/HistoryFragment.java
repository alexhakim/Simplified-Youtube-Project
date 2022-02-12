package com.alexandrehakim.youtubesimplified.ui.library;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alexandrehakim.youtubesimplified.R;
import com.alexandrehakim.youtubesimplified.databinding.FragmentHistoryBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private History history;
    private FragmentHistoryBinding binding;
    ImageView videoImageView;
    ListView historyListView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> historyArrayList = new ArrayList<String>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        history =
                new ViewModelProvider(this).get(History.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        historyListView = binding.historyListView;



        FirebaseFirestore.getInstance().collection("history").document("history")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        // TODO : get array of thumbnails + array of titles
                        if (value.get("history") != null){
                            historyArrayList = (ArrayList<String>) value.get("history");

                            arrayAdapter = new ArrayAdapter(getContext(), R.layout.layout_adapterview, R.id.titleTextView, historyArrayList);
                            historyListView.setAdapter(arrayAdapter);
                        }


                    }
                });


        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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