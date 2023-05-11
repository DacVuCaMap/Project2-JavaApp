package com.example.app.DB;

import com.example.app.Entity.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO implements DBGeneric<Client>{
    private Connection conn;
    @Override
    public void insertData(Client client) {
        String sql = "INSERT INTO tblClient(clientId,clientName,dob,address,phone,image,email,roomId,citizenID)" +
                " VALUES(?,?,?,?,?,?,?,?,?) ";
        try{
            //Convert string sql to SQL Statement
            conn = MySQLConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, client.getClientId());
            pst.setString(2, client.getClientName());
            Date date = Date.valueOf(client.getClientDOB());
            pst.setDate(3, date);
            pst.setString(4, client.getClientAddress());
            pst.setString(5, client.getClientPhone());
            pst.setString(6, client.getClientImage());
            pst.setString(7, client.getRoom().getRoomId());
            pst.setString(8, client.getClientEmail());
            pst.executeUpdate();
            //con.commit(); con.close(); transaction;
        }catch(SQLException e){
            e.printStackTrace();
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
