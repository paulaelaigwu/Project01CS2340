package com.example.project01cs2340;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.todoViewHolder> {
    public Context context;
    public List<Todo> todoList;

    // Constructor
    public TodoAdapter(Context context, List<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public todoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_layout, parent, false);
        return new todoViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.todoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_task.setText(todoList.get(position).getTask());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog todoDialog = new Dialog(view.getContext());
                todoDialog.setContentView(R.layout.todo_dialog_layout);
                todoDialog.setTitle("Edit Task");
                Button ok = todoDialog.findViewById(R.id.add_button);
                ok.setText(R.string.ok);

                EditText task_input = todoDialog.findViewById(R.id.task_input);

                task_input.setText(todoList.get(position).getTask());

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Todo updatedTodo = new Todo(task_input.getText().toString());

                        todoList.set(position, updatedTodo);
                        notifyItemChanged(position);
                        todoDialog.dismiss();
                        Toast.makeText(view.getContext(), "Task Updated", Toast.LENGTH_LONG).show();
                    }
                });

                Button cancelButton = todoDialog.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        todoDialog.cancel();
                    }
                });

                todoDialog.show();
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Delete Task?");
                builder.setCancelable(true);
                builder.setCancelable(false);

                builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> {
                    todoList.remove(position);
                    notifyItemRemoved(position);
                    dialog.dismiss();
                    Toast.makeText(view.getContext(), "Task Deleted", Toast.LENGTH_LONG).show();
                });

                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class todoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_task;
        TextView tv_day;
        TextView tv_time;
        LinearLayout parentLayout;

        public todoViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_task = itemView.findViewById(R.id.todo_task);
            parentLayout = itemView.findViewById(R.id.linearLayoutTodo);
        }
    }
}