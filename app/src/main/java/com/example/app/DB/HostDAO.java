package com.example.app.DB;

import com.example.app.Entity.Host;
import com.example.app.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostDAO implements DBGeneric<Host>{
    private Connection conn;

    @Override
    public void insertData(Host host) {
        System.out.println("het1");
        String sql = "INSERT INTO tblHost(hostId,hostName,dob,address,citizenID,phone,image,email)" +
                " VALUES(?,?,?,?,?,?,?,?) ";
        try{
            //Convert string sql to SQL Statement
            conn = MySQLConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, host.getHostId());
            pst.setString(2, host.getHostName());
            Date date = Date.valueOf(host.getDob());
            pst.setDate(3, date);
            pst.setString(4, host.getAddress());
            pst.setString(5, host.getCitizenId());
            pst.setString(6, host.getHostPhone());
            pst.setString(7, host.getHostImage());
            pst.setString(8, host.getHostEmail());
            pst.executeUpdate();
            //con.commit(); con.close(); transaction;
        }catch(SQLException e){
            e.printStackTrace();
        }
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
