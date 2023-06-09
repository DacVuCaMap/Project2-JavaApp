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
                "values (?,?,?,?,?,?,?) ";
        String sql2 = "INSERT INTO tblRoomImg(roomId,img1,img2,img3,img4,img5)" +
                "values (?,?,?,?,?,?)";
        try{
            conn = MySQLConnection.getConnection();
            PreparedStatement ptm = conn.prepareStatement(sql);
            PreparedStatement ptm2 = conn.prepareStatement(sql2);
            ptm.setString(1,room.getRoomId());
            ptm.setString(2,room.getRoomNumber());
            ptm.setDouble(3,room.getPrice());
            ptm.setString(4,room.getRoomType().getLabel());
            ptm.setString(5,room.getDesRoom());
            ptm.setString(6,room.getStatus().getLabel());
            ptm.setString(7,room.getApartment().getApartmentId());

            //ptm2
            ptm2.setString(1,room.getRoomId());
            ptm2.setString(2,room.getImg1());
            ptm2.setString(3,room.getImg2());
            ptm2.setString(4,room.getImg3());
            ptm2.setString(5,room.getImg4());
            ptm2.setString(6,room.getImg5());
            // Start the transaction
            conn.setAutoCommit(false);
            ptm.executeUpdate();
            ptm2.executeUpdate();
            //commit transaction
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Room room,String id) {
        String sql="UPDATE tblroom SET roomNumber = ?, price = ?, " +
                "typeRoom = ?, desRoom = ?, roomStatus = ?, clientId = ? WHERE roomId  = ?";
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,room.getRoomNumber());
            pstm.setDouble(2, room.getPrice());
            pstm.setString(3, String.valueOf(room.getRoomType()));
            pstm.setString(4, room.getDesRoom());
            pstm.setString(5, String.valueOf(room.getStatus()));
            pstm.setString(6, room.getClient().getClientId());
            pstm.setString(7, id);
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
        String sql="DELETE FROM tblroom WHERE roomId = ?";
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
    public List<Room> getAllData() {
        RoomImageDAO roomImageDAO = new RoomImageDAO();
        List<Room> roomList = new ArrayList<>();
        String sql = "Select * From tblRoom";
        try{
            conn=MySQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                String image1 =null;
                String image2 =null;
                String image3 =null;
                String image4 =null;
                String image5 =null;
                RoomType roomType = getRoomType(rs.getString("typeRoom"));
                StatusRoom statusRoom = getStatusRoom(rs.getString("roomStatus"));
                List<String> imageRoom = roomImageDAO.getRoomImageFromDB(rs.getString("roomId"));
                if (imageRoom!=null){
                     image1 = imageRoom.get(0);
                     image2 = imageRoom.get(1);
                     image3 = imageRoom.get(2);
                     image4 = imageRoom.get(3);
                     image5 = imageRoom.get(4);
                }

                if (imageRoom==null){

                }
                Apartment apartment = new ApartmentDAO().getApartmentbyId(rs.getString("apartmentId"));
                Client client = new ClientDAO().searchClientById(rs.getString("clientId"));
                roomList.add(new Room(rs.getString("roomId")
                        ,rs.getString("roomNumber")
                        ,rs.getDouble("price")
                        ,roomType
                        ,image1
                        ,image2
                        ,image3
                        ,image4
                        ,image5
                        ,statusRoom
                        ,apartment
                        ,rs.getString("desRoom")
                        ,client));
            }
            conn.close();
            return roomList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean checkUser(Room room) {
        return false;
    }
    public void deleteClient(Room room){
        String sql="Update tblRoom set ClientId=null where roomId=?";
        try{
            conn=MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,room.getRoomId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public RoomType getRoomType(String str){
        switch (str){
            case "Studio":
                return RoomType.Studio;
            case"k1n1":
                return RoomType.k1n1;
            case "Duplex":
                return RoomType.Duplex;
            case "k1n2":
                return RoomType.k1n2;
        }
        return null;
    }

    public StatusRoom getStatusRoom(String str){
        switch (str){
            case "AVAILABLE":
                return StatusRoom.AVAILABLE;
            case"OCCUPIED":
                return StatusRoom.OCCUPIED;
            case "MAINTENANCE":
                return StatusRoom.MAINTENANCE;
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
    public List<Room> roomHasClient(){
        List<Room> roomList = getAllData();
        List<Room> rs = new ArrayList<>();
        for (Room room : roomList){
            if (room.getClient()!=null){
                rs.add(room);
            }
        }
        return rs;
    }

    public Room getRoomById(String id){
        String sql = "Select * from tblroom where roomId = ?";
        Room room = new Room();
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                room.setRoomNumber(rs.getString("roomNumber"));
                room.setPrice(Double.parseDouble(rs.getString("price")));
                room.setRoomType(RoomType.valueOf(rs.getString("typeRoom")));
                room.setStatus(StatusRoom.valueOf(rs.getString("roomStatus")));
                room.setDesRoom(rs.getString("desRoom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

}
