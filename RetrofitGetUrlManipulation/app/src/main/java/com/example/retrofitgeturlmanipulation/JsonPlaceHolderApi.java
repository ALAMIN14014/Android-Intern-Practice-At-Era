package com.example.retrofitgeturlmanipulation;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("/posts")
    Call<List<Post>> getPost();


//    @GET("posts/1/comments")
    //  Call<List<Comment>> getComment();


    //above updated
    //  @GET("posts/{id}/comments")
    //  Call<List<Comment>> getComment(@Path("id") int postId);

    //usimng url above updated

      @GET
      Call<List<Comment>> getComment(@Url String url);


//another updated

    //  @GET("/comments")
    // Call<List<Comment>> getComment(@Query("postId") int postId);

    //another more updated
    // add it at the main url end point       &_sort=id&_order=desc

//     @GET("/comments")
//    Call<List<Comment>> getComment(
//
//            //@Query("postId") int postId,
//            //@Query("postId") int postId2,
//            //for getting more postId
//            @Query("postId") Integer[] postId,
//
//            @Query("_sort") String sort,
//            @Query("_order") String order
//
//
//     );
//
//
//
//

//    more complex

    @GET("/comments")
    Call<List<Comment>> getComment(@QueryMap Map<String, String> parameters);




}
