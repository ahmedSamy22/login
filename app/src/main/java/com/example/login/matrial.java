package com.example.login;

import java.util.HashMap;
import java.util.Map;

public class matrial {
    public String num;
    public String title;
    public String description;

    matrial(){}

    public matrial(String num,String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getnum() {
        return num;
    }

    public Map<String,Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
       // result.put("num", num);
        result.put("title", title);
        result.put("description", description);
        return result;
    }
}
