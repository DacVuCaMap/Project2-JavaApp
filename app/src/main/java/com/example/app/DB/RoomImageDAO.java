package com.example.app.DB;

import com.example.app.Entity.Enum.RoomType;
import com.example.app.Entity.Enum.StatusRoom;
import com.example.app.Entity.Room;
import com.example.app.Entity.RoomImg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomImageDAO {
    private Connection conn;
    public List<String> getRoomImageFromDB(String roomId){
        List<String> roomImage = new ArrayList<>();
        String sql= "SELECT * from tblroomimg where roomId = ?";
        try{
            conn = MySQLConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,roomId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                roomImage.add(rs.getString("img1"));
                roomImage.add(rs.getString("img2"));
                roomImage.add(rs.getString("img3"));
                roomImage.add(rs.getString("img4"));
                roomImage.add(rs.getString("img5"));
                return roomImage;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void update(RoomImg roomImg, String id) {
        String sql="UPDATE tblroomimg SET img1 = ?, img2 = ?, img3 = ?, img4 = ?, img5 = ? WHERE roomId  = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,roomImg.getImg1());
            pstm.setString(2, roomImg.getImg2());
            pstm.setString(3, roomImg.getImg3());
            pstm.setString(4, roomImg.getImg4());
            pstm.setString(5,roomImg.getImg5());
            pstm.setString(6, id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RoomImg getRoomImageById(String id){
        String sql = "Select * from tblroomimg where roomId = ?";
        RoomImg roomImg = new RoomImg();
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                roomImg.setImg1(rs.getString("img1"));
                roomImg.setImg2(rs.getString("img2"));
                roomImg.setImg3(rs.getString("img3"));
                roomImg.setImg4(rs.getString("img4"));
                roomImg.setImg5(rs.getString("img5"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomImg;
    }
}
