package com.example.project01cs2340.ui.slideshow;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project01cs2340.AssignmentAdapter;
import com.example.project01cs2340.AssignmentModel;
import com.example.project01cs2340.R;
import com.example.project01cs2340.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    public static final int ADD_ASSIGNMENT_REQUEST_CODE = 1;

    private final List<AssignmentModel> assignmentList = new ArrayList<>();



    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slideshow, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewAssignments);
        AssignmentAdapter adapter = new AssignmentAdapter(assignmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}