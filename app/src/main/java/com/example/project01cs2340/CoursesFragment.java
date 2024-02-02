package com.example.project01cs2340;


import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;



public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter courseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "Courses App";
    List<Course> courseList;
    EditText id_input, title_input, professor_input, time_input;
    CheckBox monday, tuesday, wednesday, thursday, friday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        courseList = CourseApplication.getCourseList();

        Log.d(TAG, "onCreate: " + courseList.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add_new_course_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog courseDialog = new Dialog(requireActivity());
                courseDialog.setContentView(R.layout.course_dialog_layout);
                courseDialog.setTitle("Add New Course");


                id_input = courseDialog.findViewById(R.id.id_input);
                title_input = courseDialog.findViewById(R.id.title_input);
                professor_input = courseDialog.findViewById(R.id.professor_input);
                monday = (CheckBox) courseDialog.findViewById(R.id.monday);
                tuesday = (CheckBox) courseDialog.findViewById(R.id.tuesday);
                wednesday = (CheckBox) courseDialog.findViewById(R.id.wednesday);
                thursday = (CheckBox) courseDialog.findViewById(R.id.thursday);
                friday = (CheckBox) courseDialog.findViewById(R.id.friday);
                time_input = courseDialog.findViewById(R.id.time_input);


                Button addButton = courseDialog.findViewById(R.id.add_button);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String m = monday.isChecked() ? "M" : "";
                        String t = tuesday.isChecked() ? "T" : "";
                        String w = wednesday.isChecked() ? "W" : "";
                        String th = thursday.isChecked() ? "Th" : "";
                        String f = friday.isChecked() ? "F" : "";
                        String days = m + " " + t + " " + w + " " + th + " " + f;

                        Course newCourse = new Course(id_input.getText().toString(), title_input.getText().toString(),
                                    professor_input.getText().toString(), days, time_input.getText().toString());

                        courseList.add(newCourse);
                        courseDialog.dismiss();
                        Toast.makeText(getActivity(), "New Course Added", Toast.LENGTH_LONG).show();
                    }
                });

                Button cancelButton = courseDialog.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        courseDialog.cancel();
                    }
                });

                courseDialog.show();
            }
        });


        recyclerView = (RecyclerView) getView().findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(getContext(), courseList);
        recyclerView.setAdapter(courseAdapter);
    }
}
