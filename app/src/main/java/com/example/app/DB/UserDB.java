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
        String sql="insert into tbladmin(id,adminName,adminEmail,adminPass) VALUES (?,?,?,?)";
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
    public void updateNoImg(User user, String id) {

    }

    @Override
    public void delete(String i) {

    }


    @Override
    public List<User> getAllData() {
        List<User> userList = new ArrayList<>();
        String sql="SELECT * from tbladmin";
        try {
            conn = MySQLConnection.getConnection();
            Statement stm=conn.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next()){
                userList.add(new User(rs.getString("id"),rs.getString("adminEmail"),rs.getString("adminName"),rs.getString("adminPass")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public boolean checkUser(User user) {
        String sql="SELECT * FROM tbladmin";
        try{
         conn = MySQLConnection.getConnection();
         Statement stm = conn.createStatement();
         ResultSet rs = stm.executeQuery(sql);
         while (rs.next()){
             if (rs.getString("adminEmail").equals(user.getUserMail())){
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
    public User getUserByMail(String str){
        List<User> userList = getAllData();
        for (User user:userList){
            if (user.getUserMail().equals(str)){
                return user;
            }
        }
        return null;
    }
    public void changeName(String name,String id){
        String sql="Update tbladmin set adminName=? where id=?";
        try{
            conn=MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,name);
            pstm.setString(2,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void changePassword(String pwd,String id){
        String sql="Update tbladmin set adminPass=? where id=?";
        try{
            conn=MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,pwd);
            pstm.setString(2,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkMailUnique(String mail){
        List<User> userList = getAllData();
        for (User user : userList){
            System.out.println(user);
            if (user.getUserMail().equals(mail)){
                return false;
            }
        }
        return true;
    }
}
