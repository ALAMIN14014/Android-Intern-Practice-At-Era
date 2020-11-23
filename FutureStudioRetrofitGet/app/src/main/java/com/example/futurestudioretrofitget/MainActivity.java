package com.example.futurestudioretrofitget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ApiInterface apiInterface;
    //private List<Contact> contacts;
    private List<Contact>contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviewid);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Contact>> call = apiInterface.getcontact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contacts = response.body();

                for (Contact contact : contacts) {
                    String content = "";
                    content += "ID :" + contact.getId() + " \n";
                    content += "Name : " + contact.getName() + " \n";
                    content += "Email :" + contact.getEmail() + " \n\n";
                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
             //   Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
                textView.setText(t.getMessage());

            }
        });

    }
}