package com.example.zhihuribao.main.bean;

import java.io.Serializable;

/**
 * Created by banker_test on 2017/9/9.
 */

public class UserBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
