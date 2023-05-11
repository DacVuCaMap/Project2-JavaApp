package com.example.app.DB;

import com.example.app.Entity.Host;
import com.example.app.Entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HostDAO implements DBGeneric<Host>{
    private Connection conn;

    @Override
    public void insertData(Host host) {

    }

    @Override
    public List<Host> getAllData() {
        List<Host> hostList = new ArrayList<>();
        String sql="SELECT * from tblHost";
        try {
            conn = MySQLConnection.getConnection();
            Statement stm=conn.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next()){
                Host newHost = new Host(
                        rs.getString("hostId")
                        ,rs.getString("hostName")
                        ,rs.getDate("dob").toLocalDate()
                        ,rs.getString("address")
                        ,rs.getString("citizenID")
                        ,rs.getString("image")
                        ,rs.getString("email")
                        ,rs.getString("phone")
                );
                hostList.add(newHost);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hostList;
    }

    @Override
    public boolean checkUser(Host host) {
        return false;
    }
}
