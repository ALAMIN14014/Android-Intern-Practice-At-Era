package com.example.myspinnerselecteditemwithlistener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

private  String[] countryName;
private  String[] population;
private  int[] flags;
private LayoutInflater inflater;

    public CustomAdapter( Context context,int[] flags, String[] countryName, String[] population) {
        this.countryName = countryName;
        this.population = population;
        this.flags = flags;
        this.context = context;
    }

    Context context;

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
        if(convertView==null)
        {
           LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
          convertView= layoutInflater.inflate(R.layout.sample_view,null,false);

        }
        ImageView imageView=convertView.findViewById(R.id.imageviewid);
        imageView.setImageResource(flags[position]);
        TextView country=convertView.findViewById(R.id.countrynameid);
        country.setText(countryName[position]);
        TextView populationNumber=convertView.findViewById(R.id.populationid);
        populationNumber.setText(population[position]);

        return convertView;
    }
}
