package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    private TextView tx1;
    private MyAPIService myAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx1 = findViewById(R.id.tx1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(MyAPIService.class);
        //getPosts();
        //getComments();
        createPost();
    }
    public void getPosts(){
        /*Map<String,String> paramaters = new HashMap<>();
        paramaters.put("userId","3");
        paramaters.put("_sort" ,"id");
        paramaters.put("_order" ,"desc");*/
        Call<List<Albums>> call = myAPIService.getPosts(new int[]{2,4},"id","dest");
        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if(!response.isSuccessful()){
                    tx1.setText("Code:"+response.code());
                    return;
                }
                List<Albums> posts = response.body();
                for(Albums albums : posts){                 //把albums依序讀成posts陣列的值
                    String content = "";
                    content+= "ID: "+albums.getId()+"\n";
                    content+= "User ID: "+albums.getUserId()+"\n";
                    content+= "Title: "+albums.getTitle()+"\n";
                    content+= "Text: "+albums.getText()+"\n\n";

                    tx1.append(content);            //在原本內容後面添加，跟setText不同，setText是直接洗掉之前的。
                }
            }
            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                tx1.setText(t.getMessage());
            }
        });
    }
    public void getComments(){
        Call<List<Comments>> call = myAPIService.getComments(3);
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(!response.isSuccessful()){
                    tx1.setText("Code:"+response.code());
                    return;
                }
                List<Comments> comments = response.body();
                for(Comments comment : comments){
                    String content = "";
                    content+= "Post ID: "+comment.getPostId()+"\n";
                    content+= "ID: "+comment.getId()+"\n";
                    content+= "Name: "+comment.getName()+"\n";
                    content+= "Email: "+comment.getEmail()+"\n";
                    content+= "Text: "+comment.getText()+"\n\n";

                    tx1.append(content);            //在原本內容後面添加，跟setText不同，setText是直接洗掉之前的。
                }
            }
            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                tx1.setText(t.getMessage());
            }
        });
    }
    public void createPost(){
        Post post = new Post(23,"New Title","New Text");

        /*Map<String,String> fields = new HashMap<>();
        fields.put("userId","23");
        fields.put("title","New Title");*/
        Call<Post> call = myAPIService.createPost(24,"New Title","New Text");

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    tx1.setText("Code:"+response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content +="Code: " + response.code() + "\n";
                content +="ID: " + postResponse.getId()+ "\n";
                content +="User ID: " + postResponse.getUserId()+ "\n";
                content +="Title: " + postResponse.getTitle()+ "\n";
                content +="Text: " + postResponse.getText()+ "\n";

                tx1.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tx1.setText(t.getMessage());
            }
        });
    }
}