package com.example.project01cs2340;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;



public class TodoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter todoAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "Todo App";
    List<Todo> todoList;
    EditText task_input;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        todoList = TodoApplication.getTodoList();

        Log.d(TAG, "onCreate: " + todoList.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button clearButton = view.findViewById(R.id.clear_button);

        view.findViewById(R.id.add_new_task_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog taskDialog = new Dialog(requireActivity());
                taskDialog.setContentView(R.layout.todo_dialog_layout);
                taskDialog.setTitle("Add New Task");

                task_input = taskDialog.findViewById(R.id.task_input);

                Button addButton = taskDialog.findViewById(R.id.add_button);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Todo newTask = new Todo(task_input.getText().toString());

                        todoList.add(newTask);
                        clearButton.setVisibility(View.VISIBLE);
                        taskDialog.dismiss();
                        Toast.makeText(getActivity(), "New Task Added", Toast.LENGTH_LONG).show();

                    }
                });

                Button cancelButton = taskDialog.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taskDialog.cancel();
                    }
                });

                taskDialog.show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Clear List?");
                builder.setCancelable(true);

                builder.setPositiveButton("Clear", (DialogInterface.OnClickListener) (dialog, which) -> {
                    todoList.clear();
                    todoAdapter.notifyDataSetChanged();
                    clearButton.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                    Toast.makeText(view.getContext(), "List Cleared", Toast.LENGTH_LONG).show();
                });

                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        recyclerView = (RecyclerView) requireActivity().findViewById(R.id.recycleViewTodo);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        todoAdapter = new TodoAdapter(getContext(), todoList);
        recyclerView.setAdapter(todoAdapter);
    }
}