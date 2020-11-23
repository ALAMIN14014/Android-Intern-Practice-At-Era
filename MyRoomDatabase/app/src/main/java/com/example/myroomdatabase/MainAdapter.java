package com.example.myroomdatabase;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;
//create constructor

    public MainAdapter(Activity context, List<MainData> dataList) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initial view
        View view = LayoutInflater.from(context).inflate(R.layout.list_row_main, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //initial main data
        MainData data = dataList.get(position);
//initial database
        database = RoomDB.getInstance(context);
        //set text on text view
        holder.textView.setText(data.getText());
        holder.btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initial main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //get id
                final int sID = d.getID();
                //get text
                String sText = d.getText();
                //create dialog

                final Dialog dialog = new Dialog(context);
                //set context view
                dialog.setContentView(R.layout.dialog_update);

                //initial width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //initial height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set dialog
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                //initla and assign variable
                final EditText editText = dialog.findViewById(R.id.dialog_edit_text_id);
                Button UpdateButton = dialog.findViewById(R.id.dialog_update_button);
                //set text
                editText.setText(sText);
                UpdateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //get updated text from edit text
                        String utext = editText.getText().toString().trim();
                        //update database text
                        database.mainDao().update(sID, utext);
                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getall());
                        notifyDataSetChanged();
                    }
                });

            }
        });
        holder.btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initial main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //delete text from database
                database.mainDao().delete(d);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }


        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btedit, btdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_id);
            btedit = itemView.findViewById(R.id.edit_button_id);
            btdelete = itemView.findViewById(R.id.delete_button_id);
        }
    }
}
