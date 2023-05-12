package com.example.app.DB;

import com.example.app.Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO implements DBGeneric<Client>{
    private Connection conn;
    @Override
    public void insertData(Client client) {

    }

    @Override
    public void update(Client client) {
        String sql="UPDATE tblClient SET clientId = ?, clientImage = ?, clientName = ?," +
                "email = ?, phone = ?, dob = ?, addess = ?, citizenID = ? WHERE clientId  = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,client.getClientId());
            pstm.setString(2,client.getClientImage());
            pstm.setString(3,client.getClientName());
            pstm.setString(4,client.getClientEmail());
            pstm.setString(5, client.getClientPhone());
            pstm.setString(6,client.getClientAddress());
            pstm.setString(7, String.valueOf(client.getClientDOB()));
            pstm.setString(8, client.getCitizenID());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
