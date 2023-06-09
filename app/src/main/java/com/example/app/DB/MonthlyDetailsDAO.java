package com.example.app.DB;

import com.example.app.Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthlyDetailsDAO implements DBGeneric<MonthlyDetails>{

    @Override
    public void insertData(MonthlyDetails monthlyDetails) {
        String sql="INSERT INTO tblMonthlyDetails(id,client_name,client_phone,client_mail,host_name,host_phone" +
                ",apartment_name,room_number,electric_price,electric_number,water_price,water_number,service_price,create_at,month_year,roomId)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,monthlyDetails.getId());
            pstm.setString(2,monthlyDetails.getClient().getClientName());
            pstm.setString(3,monthlyDetails.getClient().getClientPhone());
            pstm.setString(4,monthlyDetails.getClient().getClientEmail());
            pstm.setString(5,monthlyDetails.getRoom().getApartment().getHost().getHostName());
            pstm.setString(6,monthlyDetails.getRoom().getApartment().getHost().getHostPhone());
            pstm.setString(7,monthlyDetails.getRoom().getApartment().getApartmentName());
            pstm.setString(8,monthlyDetails.getRoom().getRoomNumber());
            pstm.setDouble(9,monthlyDetails.getElectricPrice());
            pstm.setInt(10,monthlyDetails.getElectricNumber());
            pstm.setDouble(11,monthlyDetails.getWaterPrice());
            pstm.setInt(12,monthlyDetails.getWaterNumber());
            pstm.setDouble(13,monthlyDetails.getServicePrice());
            pstm.setDate(14, Date.valueOf(monthlyDetails.getCreate_at()));
            pstm.setString(15,monthlyDetails.getMonth_year());
            pstm.setString(16,monthlyDetails.getRoom().getRoomId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MonthlyDetails monthlyDetails, String i) {

    }

    @Override
    public void updateNoImg(MonthlyDetails monthlyDetails, String id) {

    }

    @Override
    public void delete(String i) {

    }

    @Override
    public List<MonthlyDetails> getAllData() {
        List<MonthlyDetails> monthlyDetailsList = new ArrayList<>();
        String sql="SELECT * FROM tblMonthlyDetails";
        try{
            Connection con = MySQLConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                Client client = new Client(null,null,rs.getString("client_name"),rs.getString("client_mail"),rs.getString("client_phone"),null,null,null);
                Host host = new Host(null,rs.getString("host_name"),null,null,null,null,null,rs.getString("host_phone"));
                Apartment apartment = new Apartment(null,host,rs.getString("apartment_name"),null,null);
                Room room = new Room(rs.getString("roomId"),rs.getString("room_number"),0,null,null,null,null,null,null,null,apartment,null,client);
                monthlyDetailsList.add(
                        new MonthlyDetails(
                                rs.getString("id"),
                                client,
                                room,
                                rs.getDouble("electric_price"),
                                rs.getInt("electric_number"),
                                rs.getDouble("water_price"),
                                rs.getInt("water_number"),
                                rs.getDouble("service_price"),
                                rs.getDate("create_at").toLocalDate(),
                                rs.getString("month_year")
                        )
                );
            }
            return monthlyDetailsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUser(MonthlyDetails monthlyDetails) {
        return false;
    }
    public List<MonthlyDetails> getByRoom(String id){
        System.out.println(id);
        List<MonthlyDetails> monthlyDetailsList = getAllData();
        List<MonthlyDetails> rs=new ArrayList<>();
        if (monthlyDetailsList.size()>0){
            for (MonthlyDetails monthlyDetails : monthlyDetailsList){
                if (monthlyDetails.getRoom().getRoomId().equals(id)){
                    rs.add(monthlyDetails);
                }
            }
        }
        return rs;
    }
    public MonthlyDetails getById(String id){
        List<MonthlyDetails> monthlyDetailsList = getAllData();
        for (MonthlyDetails monthlyDetails : monthlyDetailsList){
            if (monthlyDetails.getId().equals(id)){
                return monthlyDetails;
            }
        }
        return null;
    }
}
