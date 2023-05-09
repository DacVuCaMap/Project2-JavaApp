package com.example.app.DB;

import com.example.app.Entity.Client;

import java.util.List;

public class ClientDAO implements DBGeneric<Client>{

    @Override
    public void insertData(Client client) {

    }

    @Override
    public List<Client> getAllData() {

        return null;
    }

    @Override
    public boolean checkUser(Client client) {
        return false;
    }
}
