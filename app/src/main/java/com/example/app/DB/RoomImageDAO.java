package com.example.app.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomImageDAO {
    private Connection conn;
    public List<String> getRoomImageFromDB(String roomId){
        List<String> roomImage = new ArrayList<>();
        String sql="select * from tblRoomImg where roomId='"+roomId+"'";
        int count=0;
        try{
            conn = MySQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                count++;
                roomImage.add(rs.getString("linkTo"));
            }
            while (count<5){
                count++;
                roomImage.add(null);
            }
            return roomImage;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
