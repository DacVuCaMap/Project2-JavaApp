package com.example.app.DB;

import com.example.app.Entity.Apartment;
import com.example.app.Entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO implements DBGeneric<Apartment>{
    private Connection conn;
    @Override
    public void insertData(Apartment apartment) {
        String sql = "INSERT INTO tblApartment(apartmentId,host,apartmentName,address,apartmentImage)" +
                " VALUES(?,?,?,?,?,?) ";
        try{
            //Convert string sql to SQL Statement
            conn = MySQLConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, apartment.getApartmentId());
            pst.setString(2, apartment.getHost().getHostId());
            pst.setString(3, apartment.getHost().getHostId());
            pst.setString(4, apartment.getApartmentName());
            pst.setString(5, apartment.getAddress());
            pst.setString(6, apartment.getApartmentImage());
            pst.executeUpdate();
            //con.commit(); con.close(); transaction;
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public List<Apartment> getAllData() {

        return null;
    }

    @Override
    public boolean checkUser(Apartment apartment) {
        return false;
    }


}
