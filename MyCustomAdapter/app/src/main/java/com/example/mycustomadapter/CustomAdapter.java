package com.example.mycustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    int[] flags;
    String[] countryName;
    Context context;
    private LayoutInflater inflater;

    CustomAdapter(Context context, String[] countryName, int[] flags) {
        this.context = context;
        this.countryName = countryName;
        this.flags = flags;

    }

    @Override
    public int getCount() {
        return countryName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//layouy k inflate korar jonno ready korlm
            convertView = inflater.inflate(R.layout.sample_view, parent, false);//layout k view convert kore return korbe
        }

        ImageView imageView = convertView.findViewById(R.id.imageviewid);

        TextView textView = convertView.findViewById(R.id.countryNameid);

        imageView.setImageResource(flags[position]);

        textView.setText(countryName[position]);

        return convertView;
    }
}
