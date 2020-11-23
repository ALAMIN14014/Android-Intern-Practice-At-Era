package com.example.recycleviewfromanisul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String[] title, desc;
    int[] flags;

    public MyAdapter(Context context, String[] title, String[] desc, int[] flags) {
        this.context = context;
        this.title = title;
        this.desc = desc;
        this.flags = flags;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //view a convert hbe
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titletextview.setText(title[position]);
        holder.desctextview.setText(desc[position]);
        holder.flagImageview.setImageResource(flags[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titletextview, desctextview;
        ImageView flagImageview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titletextview = itemView.findViewById(R.id.titleid);
            desctextview = itemView.findViewById(R.id.descriptionid);
            flagImageview = itemView.findViewById(R.id.imageid);
        }
    }


}
