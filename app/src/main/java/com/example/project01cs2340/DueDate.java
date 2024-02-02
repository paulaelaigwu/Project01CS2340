package com.example.project01cs2340;

import java.util.Comparator;

public class DueDate implements Comparator<Assignment> {
    @Override
    public int compare(Assignment assignment1, Assignment assignment2) {
        return assignment1.getDateDue().compareTo(assignment2.getDateDue());
    }
}
