package ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitbasic.R;

public class recyclerviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView nam, varsity;
    public recyclerviewholder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        nam.findViewById(R.id.nameid);
        varsity.findViewById(R.id.varsityid);
    }

    @Override
    public void onClick(View view) {

    }
}
