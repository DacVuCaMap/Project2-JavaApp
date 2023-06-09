package com.example.app.DB;

import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.example.app.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostDAO implements DBGeneric<Host>{
    private Connection conn;

    @Override
    public void insertData(Host host) {

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
    public void delete(String i) {
        String sql="DELETE FROM tblhost WHERE hostId = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,i);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hostList;
    }
    @Override
    public void update(Host host, String id) {
        String sql="UPDATE tblHost SET hostId = ?, hostName = ?, dob = ?, " +
                "address = ?, citizenID = ?,  phone = ?, image = ?, email = ? WHERE hostId  = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,host.getHostId());
            pstm.setString(2,host.getHostName());
            pstm.setString(3, String.valueOf(host.getDob()));
            pstm.setString(4,host.getAddress());
            pstm.setString(5, host.getCitizenId());
            pstm.setString(6,host.getHostPhone());
            pstm.setString(7,host.getHostImage());
            pstm.setString(8, host.getHostEmail());
            pstm.setString(9,id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNoImg(Host host, String id) {
        String sql="UPDATE tblHost SET hostId = ?, hostName = ?, dob = ?, " +
                "address = ?, citizenID = ?,  phone = ?, email = ? WHERE hostId  = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,host.getHostId());
            pstm.setString(2,host.getHostName());
            pstm.setString(3, String.valueOf(host.getDob()));
            pstm.setString(4,host.getAddress());
            pstm.setString(5, host.getCitizenId());
            pstm.setString(6,host.getHostPhone());
            pstm.setString(7, host.getHostEmail());
            pstm.setString(8,id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Host getHostById(String id) {
        String sql = "Select * from tblhost where hostId = ?";
        Host host = new Host();
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                host.setHostId(id);
                host.setHostImage(rs.getString("image"));
                host.setHostName(rs.getString("hostName"));
                host.setHostEmail(rs.getString("email"));
                host.setHostPhone(rs.getString("phone"));
                host.setAddress(rs.getString("address"));
                host.setDob(rs.getDate("dob").toLocalDate());
                host.setCitizenId(rs.getString("citizenID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return host;
    }

    @Override
    public boolean checkUser(Host host) {
        return false;
    }
}
