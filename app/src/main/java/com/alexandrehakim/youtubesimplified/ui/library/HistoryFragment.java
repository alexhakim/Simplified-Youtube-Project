package com.alexandrehakim.youtubesimplified.ui.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alexandrehakim.youtubesimplified.R;
import com.alexandrehakim.youtubesimplified.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private History history;
    private FragmentHistoryBinding binding;
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

        // TODO: finalize

        arrayAdapter = new ArrayAdapter(getContext(), R.layout.layout_adapterview, R.id.titleTextView, historyArrayList);
        historyListView.setAdapter(arrayAdapter);

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