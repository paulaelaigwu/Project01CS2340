package com.example.project01cs2340;

import android.graphics.Color;
public class Todo {
    private String task;

    public Todo(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "task='" + task + '\'' +
                '}';
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
