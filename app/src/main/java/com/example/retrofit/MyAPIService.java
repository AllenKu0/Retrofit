package com.example.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MyAPIService {

    @GET("posts")   //網址後的內容，ex:https://jsonplaceholder.typicode.com/posts，這裡輸入的是最後的posts
    Call<List<Albums>> getPosts();

    @GET("posts/2/comments")
    Call<List<Comments>> getComments();

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

    @GET("posts")
    // int 可以改為Interger，這樣可以設null
    //Query數據在URL上
    Call<List<Albums>> getPosts(
            @Query("userId") int userId[],
            @Query("_sort") String sort,
            @Query("_order") String order
    );
    @GET("posts")
    Call<List<Albums>> getPosts(
            @QueryMap Map<String,String> parameters
    );

    @POST ("posts")// 用@Body表示要傳送Body資料
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    //@Field數據在請求體上
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @FieldMap Map<String,String> field
    );
}