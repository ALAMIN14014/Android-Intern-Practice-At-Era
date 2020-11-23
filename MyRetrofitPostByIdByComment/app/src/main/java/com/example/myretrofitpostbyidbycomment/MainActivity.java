package com.example.myretrofitpostbyidbycomment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textviewid);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //getpost();
        //getcomments();
        createPost();
    }

    public void getpost() {

        Call<List<Post>> call = jsonPlaceHolderApi.getpost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {

                    textView.setText("code : " + response.code());
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID " + post.getId() + " \n";
                    content += "UserId " + post.getUserId() + " \n";
                    content += "title " + post.getTitle() + " \n";
                    content += "Text " + post.getText() + " \n\n";

                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                textView.setText(t.getMessage());
            }
        });
    }

    private void getcomments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getcomments(4);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {

                    textView.setText("code : " + response.code());
                }
                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID :" + comment.getId() + " \n";
                    content += "PostId : " + comment.getPostId() + " \n";
                    content += "Name :" + comment.getName() + " \n";
                    content += "Email :" + comment.getEmail() + " \n";
                    content += "Text :" + comment.getText() + " \n\n";

                    textView.append(content);

                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });


    }


    private void createPost() {

        Post post = new Post(23, "New title", "New Text");
        Call<Post> call = jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {

                    textView.setText("code : " + response.code());
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code :" + response.code() + " \n";
                content += "ID :" + postResponse.getId() + " \n";
                content += "UserId :" + postResponse.getUserId() + " \n";
                content += "title :" + postResponse.getTitle() + " \n";
                content += "Text :" + postResponse.getText() + " \n\n";

                textView.setText(content);


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }


}