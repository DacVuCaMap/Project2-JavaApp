package com.example.app.DB;

import com.example.app.Controller.Add.AddApartment;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Enum.RoomType;
import com.example.app.Entity.Enum.StatusRoom;
import com.example.app.Entity.Host;
import com.example.app.Entity.Room;
import javafx.scene.paint.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDAO implements DBGeneric<Room>{
    private Connection conn;

    @Override
    public void insertData(Room room) {
        String sql = "Insert into tblRoom(roomId,roomNumber,price,TypeRoom,desRoom,roomStatus,apartmentId) " +
                "values (?,?,?,?,?,?,?)";
        try{
            conn = MySQLConnection.getConnection();
            PreparedStatement ptm = conn.prepareStatement(sql);
            ptm.setString(1,room.getRoomId());
            ptm.setString(2,room.getRoomNumber());
            ptm.setDouble(3,room.getPrice());
            ptm.setString(4,room.getRoomType().getLabel());
            ptm.setString(5,room.getDesRoom());
            ptm.setString(6,room.getStatus().getLabel());
            ptm.setString(7,room.getApartment().getApartmentId());
            ptm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Room room,String str) {

    }

    @Override
    public void delete(String i) {

    }

    @Override
    public List<Room> getAllData() {
        RoomImageDAO roomImageDAO = new RoomImageDAO();
        List<Room> roomList = new ArrayList<>();
        String sql = "Select * From tblRoom";
        try{
            conn=MySQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                RoomType roomType = getRoomType(rs.getString("typeRoom"));
                StatusRoom statusRoom = getStatusRoom(rs.getString("roomStatus"));
                List<String> imageRoom = roomImageDAO.getRoomImageFromDB(rs.getString("roomId"));
                Apartment apartment = new ApartmentDAO().getApartmentbyId(rs.getString("apartmentId"));

                roomList.add(new Room(rs.getString("roomId")
                        ,rs.getString("roomNumber")
                        ,rs.getDouble("price")
                        ,roomType
                        ,imageRoom.get(0)
                        ,imageRoom.get(1)
                        ,imageRoom.get(2)
                        ,imageRoom.get(3)
                        ,imageRoom.get(4)
                        ,statusRoom
                        ,apartment
                        ,rs.getString("desRoom")));
            }
            return roomList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean checkUser(Room room) {
        return false;
    }
    public RoomType getRoomType(String str){
        switch (str){
            case "Studio":
                return RoomType.Studio;
            case"k1n1":
                return RoomType.k1n1;
            case "Duplex":
                return RoomType.Duplex;
            case "k2n1":
                return RoomType.k2n1;
        }
        return null;
    }
    public StatusRoom getStatusRoom(String str){
        switch (str){
            case "AVAILABLE":
                return StatusRoom.A;
            case"OCCUPIED":
                return StatusRoom.O;
            case "MAINTENANCE":
                return StatusRoom.M;
        }
        return null;
    }
    public static Room searchRoomById(String id){
        List<Room> list = new RoomDAO().getAllData();
        for (Room room : list){
            if (room.getRoomId().equals(id)){
                return room;
            }
        }
        return null;

    }
    public static Room getRoomMap(String str){
        Map<String,Room> mapRoom = new HashMap<>();
        for(Room room : new RoomDAO().getAllData()){
            mapRoom.put("Room number:"+room.getRoomNumber()+"   Appartment:"+room.getApartment().getApartmentName(),room);
        }
        Room room = mapRoom.get(str);
        return room;
    }
    public void changeRoomStatus(Room room){
        String sql = "update tblroom set roomStatus = ? where roomId=?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,"OCCUPIED");
            pstm.setString(2,room.getRoomId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
