package com.example.app.DB;

import com.example.app.Controller.Add.AddApartment;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Host;
import com.example.app.Entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO implements DBGeneric<Apartment>{
    private Connection conn;
    @Override
    public void insertData(Apartment apartment) {
        String sql = "INSERT INTO tblApartment(apartmentId,hostId,apartmentName,address,image)" +
                " VALUES(?,?,?,?,?) ";
        try{
            //Convert string sql to SQL Statement
            conn = MySQLConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, apartment.getApartmentId());
            pst.setString(2, apartment.getHost().getHostId());
            pst.setString(3, apartment.getApartmentName());
            pst.setString(4, apartment.getAddress());
            pst.setString(5, apartment.getApartmentImage());
            pst.executeUpdate();
            //con.commit(); con.close(); transaction;
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Apartment apartment) {

    }


    @Override
    public List<Apartment> getAllData() {
        List<Apartment> apartmentList = new ArrayList<>();
        String sql = "Select * From tblapartment";
        try{
            conn=MySQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
                while (rs.next()){
                    Host host = AddApartment.findHostById(rs.getString("hostId"));
                    apartmentList.add(new Apartment(rs.getString("apartmentId")
                            ,host
                            ,rs.getString("apartmentName")
                            ,rs.getString("address")
                            ,rs.getString("image")));
                }
                return apartmentList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUser(Apartment apartment) {
        return false;
    }
    public static Apartment getApartmentbyId(String id){
        List<Apartment> apartmentList = new ApartmentDAO().getAllData();
        for (Apartment apartment : apartmentList){
            if (apartment.getApartmentId().equals(id)){
                return apartment;
            }
        }
        return null;
    }


}
