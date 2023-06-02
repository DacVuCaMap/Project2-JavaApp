package com.example.app.DB;

import com.example.app.Controller.Add.AddApartment;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
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
    public void update(Room room,String id) {
        String sql="UPDATE tblroom SET roomNumber = ?, price = ?, " +
                "typeRoom = ?, desRoom = ?, image = ?, image2 = ?, image3 = ?, " +
                "image4 = ?, image5 = ?, roomStatus = ? WHERE roomId  = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,room.getRoomNumber());
            pstm.setDouble(2, room.getPrice());
            pstm.setString(3, String.valueOf(room.getRoomType()));
            pstm.setString(4, room.getDesRoom());
            pstm.setString(5,room.getImg1());
            pstm.setString(6,room.getImg2());
            pstm.setString(7, room.getImg3());
            pstm.setString(8, room.getImg4());
            pstm.setString(9, room.getImg5());
            pstm.setString(10, String.valueOf(room.getStatus()));
            pstm.setString(11, id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNoImg(Room room, String id) {

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
//                List<String> imageRoom = roomImageDAO.getRoomImageFromDB(rs.getString("roomId"));

                Apartment apartment = new ApartmentDAO().getApartmentbyId(rs.getString("apartmentId"));
                Client client = new ClientDAO().searchClientById(rs.getString("clientId"));
                roomList.add(new Room(rs.getString("roomId")
                        ,rs.getString("roomNumber")
                        ,rs.getDouble("price")
                        ,roomType
                        ,null
                        ,null
                        ,null
                        ,null
                        ,null
                        ,statusRoom
                        ,apartment
                        ,rs.getString("desRoom")
                        ,client));
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
    public void changeRoomStatus(Room room,String str){
        String sql = "update tblroom set roomStatus = ? where roomId=?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,str);
            pstm.setString(2,room.getRoomId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addClient(Room room, Client client){
        String sql = "update tblroom set clientId = ? where roomId=?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,client.getClientId());
            pstm.setString(2,room.getRoomId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
