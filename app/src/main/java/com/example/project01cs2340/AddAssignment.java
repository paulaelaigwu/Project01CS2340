package com.example.project01cs2340;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddAssignment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText etCourseID, etCourseName;
    private TextView tvDueDate;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        etCourseID = findViewById(R.id.et_CourseID);
        etCourseName = findViewById(R.id.editTextText2);
        tvDueDate = findViewById(R.id.et_DueDate);
        btnSave = findViewById(R.id.btn_Save);

        tvDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from input fields
                String courseID = etCourseID.getText().toString();
                String courseName = etCourseName.getText().toString();
                String dateDue = tvDueDate.getText().toString(); // Assuming this is where you want the due date

                // Create an intent to return data to MainActivity
                Intent resultIntent = new Intent();

                // Check if this is a new assignment or an edited assignment
                if (getIntent().hasExtra("isEditing")) {
                    // If it's editing, pass the original position
                    int position = getIntent().getIntExtra("position", -1);
                    resultIntent.putExtra("position", position);
                }

                resultIntent.putExtra("courseID", courseID);
                resultIntent.putExtra("courseName", courseName);
                resultIntent.putExtra("dateDue", dateDue);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Check if it's an edit operation
        if (getIntent().hasExtra("isEditing")) {
            // Retrieve existing data for editing
            String existingCourseID = getIntent().getStringExtra("existingCourseID");
            String existingCourseName = getIntent().getStringExtra("existingCourseName");
            String existingDateDue = getIntent().getStringExtra("existingDateDue");

            // Populate the fields with existing data
            etCourseID.setText(existingCourseID);
            etCourseName.setText(existingCourseName);
            tvDueDate.setText(existingDateDue);
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String formattedDate = String.format("%d/%02d/%02d", year, month + 1, dayOfMonth);
        tvDueDate.setText(formattedDate);
    }

}
