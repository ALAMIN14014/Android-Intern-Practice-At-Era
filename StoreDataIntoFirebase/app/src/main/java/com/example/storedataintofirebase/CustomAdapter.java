package com.example.storedataintofirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private List<Student> studentList;
    private Activity context;

    public CustomAdapter(Activity context, List<Student> studentList) {
        super(context, R.layout.sample_layout, studentList);
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, parent, false);
        Student student = studentList.get(position);

        TextView nametextView = view.findViewById(R.id.nametextviewid);
        TextView agetextView = view.findViewById(R.id.agetextviewid);

        nametextView.setText(student.getName());
        agetextView.setText(student.getAge());

        return view;
    }
}
