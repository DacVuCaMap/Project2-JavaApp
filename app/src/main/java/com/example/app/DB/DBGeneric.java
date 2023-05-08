package com.example.app.DB;

import java.util.List;

public interface DBGeneric<T> {
    void insertData(T t);
    List<T> getAllData();
    boolean checkUser(T t);
}
