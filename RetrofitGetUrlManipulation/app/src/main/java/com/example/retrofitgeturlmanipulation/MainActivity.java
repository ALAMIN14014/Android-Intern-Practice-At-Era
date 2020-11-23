package com.example.retrofitgeturlmanipulation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // used to instantiate retrofit

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //  getposts();
        getComments();
    }

    private void getposts() {

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

    private void getComments() {
        //more compplex

        Map<String,String>parameters=new HashMap<>();
        parameters.put("postId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");



        //     Call<List<Comment>> call = jsonPlaceHolderApi.getComment();

        //updated above
      //  Call<List<Comment>> call = jsonPlaceHolderApi.getComment(2);

      //using url above updated
        Call<List<Comment>> call = jsonPlaceHolderApi.getComment("posts/1/comments");


        //another more updated
       // Call<List<Comment>> call = jsonPlaceHolderApi.getComment(2,"id","desc");

        //Call<List<Comment>> call = jsonPlaceHolderApi.getComment(2,null,null);
      //  Call<List<Comment>> call = jsonPlaceHolderApi.getComment(1,2,null,null);

        //for getting more post id
      //  Call<List<Comment>> call = jsonPlaceHolderApi.getComment(new Integer[]{1,2,3},null,null);
      //more complex
      //  Call<List<Comment>> call = jsonPlaceHolderApi.getComment(parameters);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {

                    textView.setText("Code : " + response.code());
                    return;

                } else {
                    List<Comment> comments = response.body();
                    for (Comment comment : comments) {
                        String content = "";
                        content += "ID : " + comment.getId() + " \n";
                        content += "Post_ID : " + comment.getPostId() + " \n";
                        content += "Name : " + comment.getName() + " \n";
                        content += "Email : " + comment.getEmail() + "\n";
                        content += "Body : " + comment.getBody() + " \n\n";
                        textView.append(content);
                        content = "";
                    }


                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });


    }
}