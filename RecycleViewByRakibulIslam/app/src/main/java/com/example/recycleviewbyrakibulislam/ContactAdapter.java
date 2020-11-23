package com.example.recycleviewbyrakibulislam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder> {
    ArrayList<Contact> myContact = new ArrayList<>();

    public ContactAdapter(ArrayList<Contact> myContact) {
        this.myContact = myContact;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Contact contact = myContact.get(position);
        holder.nametext.setText(contact.getName());
        holder.phonetext.setText(contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        if (myContact.isEmpty()) {
            return 0;
        } else {
            return myContact.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nametext, phonetext;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nametext = itemView.findViewById(R.id.nametextid);
            phonetext = itemView.findViewById(R.id.phonetextid);
        }
    }
}
