package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitbasic.R;

import java.util.List;

import Model.user;
import ViewHolder.recyclerviewholder;


public class RecyclerViewAdapter extends RecyclerView.Adapter <recyclerviewholder> {
    private List<user>item;
    public RecyclerViewAdapter (List<user>list){
        this.item=list;
    }
    @NonNull
    @Override
    public recyclerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutview= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, null);
        recyclerviewholder recycler= new recyclerviewholder(layoutview);

        return recycler;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerviewholder holder, int position) {
        holder.nam.setText(item.get(position).getName());
        holder.varsity.setText(item.get(position).getUniversity());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
