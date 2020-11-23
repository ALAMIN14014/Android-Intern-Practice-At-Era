package com.example.getretrofitnestedobject.Tutorial3Class;

import java.util.List;

public class ArrayDesignClass {

    private List<ObjectDesignClass> data;

    public List<ObjectDesignClass> getData() {
        return data;
    }

    public void setData(List<ObjectDesignClass> data) {
        this.data = data;
    }

    public ArrayDesignClass() {
    }

    public ArrayDesignClass(List<ObjectDesignClass> data) {
        this.data = data;
    }


}
