package com.example.getretrofitnestedobject;

import java.util.List;

public class OurMainDataClass {

    List<ObjectDataClass>data;

    public OurMainDataClass() {
    }

    public OurMainDataClass(List<ObjectDataClass> data) {

        this.data = data;
    }

    public List<ObjectDataClass> getData() {
        return data;
    }

    public void setData(List<ObjectDataClass> data) {
        this.data = data;
    }
}

