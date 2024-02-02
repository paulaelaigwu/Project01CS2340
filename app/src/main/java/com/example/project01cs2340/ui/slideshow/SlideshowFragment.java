package com.example.project01cs2340.ui.slideshow;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.project01cs2340.R;
import com.example.project01cs2340.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //SlideshowViewModel slideshowViewModel =
          //      new ViewModelProvider(this).get(SlideshowViewModel.class);

       // binding = FragmentSlideshowBinding.inflate(inflater, container, false);
       // View root = binding.getRoot();

       // final Spinner textView = binding.spinner;
       // slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setTooltipText);
        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}