package com.example.retrofitgetfromcodinginflow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviewid);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // used to instantiate retrofit

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//to execute out network request  we have to use call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {

                    textView.setText("Code : " + response.code());
                    return;

                } else {

                    List<Post> posts = response.body();

                    for (Post post : posts) {
                        String content = "";
                        content += "ID : " + post.getId() + " \n";
                        content += "User_ID : " + post.getUserId() + " \n";
                        content += "Title : " + post.getTitle() + " \n";
                        content += "Body : " + post.getText() + " \n\n";
                        textView.append(content);
                        content = "";

                    }

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });


    }
}