package com.example.app.DB;

import com.example.app.Entity.Client;
import com.example.app.Entity.Host;

import java.util.List;

public interface DBGeneric<T> {
    void insertData(T t);

    void update(T t, String i);

    void updateNoImg(T t, String id);

    void delete(String i);
    List<T> getAllData();
    boolean checkUser(T t);
}
