package com.example.futurestudioretrofitget;


import com.google.gson.annotations.SerializedName;

public class Contact {

    private Integer id;
    @SerializedName("name")//jokhn  JSON key varibale same na hoy tokhn ei annotaion use krte hoy
    private String Name;
    private String email;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }
}

