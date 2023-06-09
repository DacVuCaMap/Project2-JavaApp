package com.example.app.DB;

import com.example.app.Entity.Client;
import com.example.app.Entity.Contract;
import com.example.app.Entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO implements DBGeneric<Contract>{
    private Connection conn;
    private List<Room> roomList=new RoomDAO().getAllData();
    private List<Client> clientList = new ClientDAO().getAllData();
    @Override
    public void insertData(Contract contract) {
        String sql="insert into tblContract(contractId,startDate,startMonth,endMonth,desContract,clientId,roomId,totalPrice)" +
                "values (?,?,?,?,?,?,?,?)";
        System.out.println(contract.getClient().getClientId());
        System.out.println(contract.getRoom().getRoomId());
        try{
            conn=MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,contract.getContractId());
            pstm.setDate(2, Date.valueOf(contract.getStartDate()));
            pstm.setDate(3,Date.valueOf(contract.getStartMonth()));
            pstm.setDate(4,Date.valueOf(contract.getEndMonth()));
            pstm.setString(5,contract.getDescription());
            pstm.setString(6,contract.getClient().getClientId());
            pstm.setString(7,contract.getRoom().getRoomId());
            pstm.setDouble(8,contract.getTotalPrice());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Contract contract, String i) {

    }

    @Override
    public void updateNoImg(Contract contract, String id) {

    }

    @Override
    public void delete(String id) {
        String sql="DELETE FROM tblContract where contractId=?";
        Contract contract = getContractById(id);
        new RoomDAO().deleteClient(contract.getRoom());
        new MonthlyDAO().deleteByContract(id);
        try {
            conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contract> getAllData() {
        List<Contract> contractList = new ArrayList<>();
        String sql="Select * from tblContract";
        try{
            conn=MySQLConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                String id =rs.getString("contractId");
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalDate startMonth = rs.getDate("startMonth").toLocalDate();
                LocalDate endMonth = rs.getDate("endMonth").toLocalDate();
                String des = rs.getString("desContract");
                Client client = searchClientById(rs.getString("clientId"));
                Room room = searchRoomById(rs.getString("roomId"));
                Double total = rs.getDouble("totalPrice");
                contractList.add(new Contract(id,startDate,startMonth,endMonth,des,client,room,total));
            }
            return contractList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUser(Contract contract) {
        return false;
    }
    public Contract searchContractByRoomId(String roomId){
        List<Contract> contractList = getAllData();
        for (Contract contract : contractList){
            if (contract.getRoom().getRoomId().equals(roomId)){
                return contract;
            }
        }
        return null;
    }
    public Contract getContractById(String id){
        List<Contract> contractList = getAllData();
        for (Contract contract : contractList){
            if (contract.getContractId().equals(id)){
                return contract;
            }
        }
        return null;
    }
    public Room searchRoomById(String id){
        for (Room room : roomList){
            if (room.getRoomId().equals(id)){
                return room;
            }
        }
        return null;
    }
    public Client searchClientById(String id){
        for (Client client : clientList){
            if (client.getClientId().equals(id)){
                return client;
            }
        }
        return null;
    }
}
