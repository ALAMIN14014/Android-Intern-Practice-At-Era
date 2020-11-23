package com.example.myroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button addButton, resetButton;
    private RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text_id);
        addButton = findViewById(R.id.addbuttonid);
        resetButton = findViewById(R.id.resetbuttonid);
        recyclerView = findViewById(R.id.recycler_id);

//initial database
        database=RoomDB.getInstance(this);
//store database vlaue in datalist
        dataList=database.mainDao().getall();
        //initial liner layout manager
        linearLayoutManager=new LinearLayoutManager(this);

        //set layout managet
        recyclerView.setLayoutManager(linearLayoutManager);
        //initial adapter
        adapter=new MainAdapter(MainActivity.this,dataList);
        recyclerView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from edit text
                String stext=editText.getText().toString().trim();
                //check condition
                if(!stext.equals("")){
                    //when text is not empty
                    // initial main data
                    MainData data=new MainData();
                    //set text on main data
                    data.setText(stext);
                    // insert text in database
                    database.mainDao().insert(data);
                    //clear edit text
                    editText.setText("");
                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getall());
                    adapter.notifyDataSetChanged();


                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all data from database
                database.mainDao().reset(dataList);
                //notify when all data deleted
                dataList.addAll(database.mainDao().getall());
                adapter.notifyDataSetChanged();
            }
        });
    }
}