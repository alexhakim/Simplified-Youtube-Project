package com.alexandrehakim.youtubesimplified.ui.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alexandrehakim.youtubesimplified.databinding.FragmentLibraryBinding;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    private LibraryViewModel libraryViewModel;
    private FragmentLibraryBinding binding;
    ListView downloadsListView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> downloadsArrayList = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        libraryViewModel =
                new ViewModelProvider(this).get(LibraryViewModel.class);

        binding = FragmentLibraryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        downloadsListView = binding.downloadsListView;

        // TODO: array adapter

        downloadsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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