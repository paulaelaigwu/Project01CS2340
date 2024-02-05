package com.example.project01cs2340.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project01cs2340.R;
import com.example.project01cs2340.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the binding layout and get the root view
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Access the TextView using binding
        final TextView textView = binding.textView3;

        // Initialize ViewModel
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Observe ViewModel data and update the TextView
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Access the CalendarView using binding
        CalendarView calendarView = binding.calendarView;

        // Get the current date in milliseconds
        long currentDateMillis = System.currentTimeMillis();

        // Set the current date to the CalendarView
        calendarView.setDate(currentDateMillis);

        // Optionally, set a listener to handle date changes
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Handle date selection changes if needed
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
