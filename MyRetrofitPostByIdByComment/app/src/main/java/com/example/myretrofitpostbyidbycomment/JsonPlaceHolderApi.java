package com.example.myretrofitpostbyidbycomment;

import java.util.List;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getpost();

   // @GET("posts/{id}/comments")
   // Call<List<Comment>>getcomments(@Path("id")int postid);

    @GET("comments")
    Call<List<Comment>>getcomments(@Query("postId") int postId);

    @POST("posts")
    Call<Post>createPost(@Body Post post);


}
