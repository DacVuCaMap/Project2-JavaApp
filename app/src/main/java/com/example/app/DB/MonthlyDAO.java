package com.example.app.DB;

import com.example.app.Entity.Contract;
import com.example.app.Entity.Monthly;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyDAO implements DBGeneric<Monthly>{
    private Connection conn = MySQLConnection.getConnection();
    private List<Contract> contractList = new ContractDAO().getAllData();
    @Override
    public void insertData(Monthly monthly) {
        String sql="INSERT INTO tblMonthly(contractId,status,month_year) values (?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,monthly.getContract().getContractId());
            pstm.setString(2,monthly.getStatus());
            pstm.setDate(3, Date.valueOf(monthly.getDate()));
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Monthly monthly, String i) {

    }

    @Override
    public void updateNoImg(Monthly monthly, String id) {

    }

    @Override
    public void delete(String i) {

    }

    @Override
    public List<Monthly> getAllData() {
        return null;
    }

    @Override
    public boolean checkUser(Monthly monthly) {
        return false;
    }
    public List<Monthly> getDataByMonth(LocalDate date){
        List<Monthly> monthlyList = new ArrayList<>();
        String sql = "SELECT * FROM tblMonthly where month_year=?";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,date.toString());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                monthlyList.add(new Monthly(0,getContractById(rs.getString("contractId"))
                        ,rs.getString("status"),rs.getDate("month_year").toLocalDate()));
            }
            return monthlyList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteContractAndMonth(Monthly monthly){
        String sql="DELETE FROM tblMonthly where contractId=? AND month_year=?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,monthly.getContract().getContractId());
            pstm.setDate(2,Date.valueOf(monthly.getDate()));
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteByContract(String id){
        String sql="DELETE FROM tblMonthly where contractId=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Contract getContractById(String id){
        for (Contract contract : contractList){
            if (contract.getContractId().equals(id)){
                return contract;
            }
        }
        return null;
    }
}
