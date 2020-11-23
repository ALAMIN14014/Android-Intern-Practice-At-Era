package com.example.getretrofitnestedobject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;

import com.example.getretrofitnestedobject.Tutorial3Class.ArrayDesignClass;
import com.example.getretrofitnestedobject.Tutorial3Class.ObjectDesignClass;
import com.example.getretrofitnestedobject.Tutorial3Class.PositionClass;
import com.example.getretrofitnestedobject.Tutorial3Class.TeamClass;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    OurRetrofitClient ourRetrofitClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://cricket.sportmonks.com/api/v2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ourRetrofitClient = retrofit.create(OurRetrofitClient.class);


        Call<TeamClass> call = ourRetrofitClient.getData(10, "0pxcdEQdsGZ7DGBUQNstGwcNmdA0pPuZ9vTimkIP1zbwefRZGiS9MtFMZbW9");

        call.enqueue(new Callback<TeamClass>() {
            @Override
            public void onResponse(Call<TeamClass> call, Response<TeamClass> response) {
                final int cid = response.body().getCountry_id();
                String cname = response.body().getName();


                Call<ArrayDesignClass> playercall = ourRetrofitClient.getPlayersData("0pxcdEQdsGZ7DGBUQNstGwcNmdA0pPuZ9vTimkIP1zbwefRZGiS9MtFMZbW9", cid);


                playercall.enqueue(new Callback<ArrayDesignClass>() {
                    @Override
                    public void onResponse(Call<ArrayDesignClass> call, Response<ArrayDesignClass> response) {
                        List<ObjectDesignClass> list = response.body().getData();
                        for (ObjectDesignClass obj : list) {

                            String dob = obj.getDateofbirth();
                            String fullname = obj.getFullname();
                            String gender = obj.getGender();


                            PositionClass positionClass = obj.getPosition();

                            String posname = positionClass.getName();

                            Log.d("cid", String.valueOf(cid));
                            Log.d("dob", String.valueOf(dob));
                            Log.d("fullname", String.valueOf(fullname));
                            Log.d("gender", String.valueOf(gender));
                            Log.d("posname", String.valueOf(posname));

                        }

                    }

                    @Override
                    public void onFailure(Call<ArrayDesignClass> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<TeamClass> call, Throwable t) {

            }
        });

    }}