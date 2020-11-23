package com.example.myloginandregistrationusingretrofit;

import com.google.gson.annotations.SerializedName;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
