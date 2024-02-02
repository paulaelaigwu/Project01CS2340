package com.example.project01cs2340;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TodoApplication extends Application {
    private static List<Todo> todoList = new ArrayList<>();

    public TodoApplication() {
    }

    public static List<Todo> getTodoList() {
        return todoList;
    }

    public static void setTodoList(List<Todo> todoList) {
        TodoApplication.todoList = todoList;
    }
}
