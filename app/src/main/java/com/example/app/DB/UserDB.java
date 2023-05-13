package com.example.app.DB;

import com.example.app.Entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB implements DBGeneric<User>{
    private Connection conn;


    @Override
    public void insertData(User user) {
        String sql="insert into admintbl(adminId,adminName,adminMail,adminPass) VALUES (?,?,?,?)";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            System.out.println(user.toString());
            pstm.setString(1,user.getUserId());
            pstm.setString(2,user.getUserName());
            pstm.setString(3,user.getUserMail());
            pstm.setString(4,user.getPwd());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user, String i) {

    }

    @Override
    public void delete(String i) {

    }


    @Override
    public List<User> getAllData() {
        List<User> userList = new ArrayList<>();
        String sql="SELECT * from admintbl";
        try {
            conn = MySQLConnection.getConnection();
            Statement stm=conn.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next()){
                userList.add(new User(rs.getString("adminId"),rs.getString("adminMail"),rs.getString("adminName"),rs.getString("adminPass")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public boolean checkUser(User user) {
        String sql="SELECT * FROM admintbl";
        try{
         conn = MySQLConnection.getConnection();
         Statement stm = conn.createStatement();
         ResultSet rs = stm.executeQuery(sql);
         while (rs.next()){
             if (rs.getString("adminMail").equals(user.getUserMail())){
                 if (BCrypt.checkpw(user.getPwd(),rs.getString("adminPass"))){
                     return true;
                 }
             }
         }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
