package com.example.app.Controller.Contract;

import com.example.app.DB.*;
import com.example.app.Entity.Client;
import com.example.app.Entity.Contract;
import com.example.app.Entity.Monthly;
import com.example.app.Entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddContract implements Initializable {
    @FXML
    private Button btnAdd;

    @FXML
    private Button cancleBtn;

    @FXML
    private ComboBox<String> clientBox;

    @FXML
    private Label clientNof;

    @FXML
    private TextField desField;

    @FXML
    private Label desNof;

    @FXML
    private ComboBox<String> monthBox;
    @FXML
    private ComboBox<String> timeBox;

    @FXML
    private Label monthNof;

    @FXML
    private Label timeNof;

    @FXML
    private ComboBox<String> roomBox;

    @FXML
    private Label roomNof;
    @FXML
    private Label labelRoomPrice;
    @FXML
    private Label labelTotal;
    private DBGeneric<Contract> contractDBGeneric = new ContractDAO();
    private DBGeneric<Monthly> monthlyDBGeneric = new MonthlyDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listTime = FXCollections.observableArrayList();
        listTime.addAll("1 month","2 month","6 month","1 year","2 year");
        clientBox.setItems(FXCollections.observableList(getClientList()));
        roomBox.setItems(FXCollections.observableList(getRoomList()));
        monthBox.setItems(FXCollections.observableList(getMonthList()));
        timeBox.setItems(listTime);
        roomBox.setOnAction(e->{
            Room room = new RoomDAO().getRoomMap(roomBox.getValue());
            labelRoomPrice.setText(room.getPrice()+" $/month");
            if (timeBox.getValue()!=null){
                getTotal(room.getPrice(),getTimeValue(timeBox.getValue()));
            }
        });
        timeBox.setOnAction(e->{
            getTimeValue(timeBox.getValue());
            if (roomBox.getValue()!=null){
                Room room = new RoomDAO().getRoomMap(roomBox.getValue());
                getTotal(room.getPrice(),getTimeValue(timeBox.getValue()));
            }
        });

        //insert
        btnAdd.setOnAction(e->{
            Scene scene = btnAdd.getScene();
            scene.setCursor(Cursor.WAIT);
            //validation
            clientNof.setText("");
            roomNof.setText("");
            monthNof.setText("");
            timeNof.setText("");
            desNof.setText("");
            boolean flag = true;
            if (clientBox.getValue()==null){
                flag=false;
                clientNof.setText("please select");
            }
            if (roomBox.getValue()==null){
                flag=false;
                roomNof.setText("please select");
            }
            if (monthBox.getValue()==null){
                flag=false;
                monthNof.setText("please select");
            }
            if (timeBox.getValue()==null){
                flag=false;
                timeNof.setText("please select");
            }
            if (desField.getText().length()<5){
                flag = false;
                desNof.setText("invalid description");
            }
            //check true to insert
            if (flag){
                String id = buildId();
                LocalDate startDate = LocalDate.now();
                LocalDate startMonth = getStartMonth(monthBox.getValue());
                LocalDate endMonth = getEnddMonth(startMonth,timeBox.getValue());
                String des = desField.getText();
                Client client = new ClientDAO().searchClientById(clientBox.getValue().substring(4,9));
                Room room = new RoomDAO().getRoomMap(roomBox.getValue());
                Double total = Double.valueOf(labelTotal.getText().replace("$",""));
                Contract contract = new Contract(id,startDate,startMonth,endMonth,des,client,room,total);
                //insert
                new ContractDAO().insertData(contract);
                new RoomDAO().changeRoomStatus(room,"OCCUPIED");
                new RoomDAO().addClient(room,client);
                insertMonthly(startMonth,endMonth,contract);
                //close
                Stage stage =(Stage) cancleBtn.getScene().getWindow();
                stage.close();
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        //cancle
        cancleBtn.setOnAction(e->{
            Stage stage =(Stage) cancleBtn.getScene().getWindow();
            stage.close();
        });
    }
    public List<String> getClientList(){
        List<String> list = new ArrayList<>();
        for (Client client : new ClientDAO().getAllData()){
                list.add("ID: "+client.getClientId()+"   Name:"+client.getClientName());
        }
        return list;
    }
    public List<String> getRoomList(){
        List<String> list = new ArrayList<>();
        for (Room room : new RoomDAO().getAllData()){
            if (room.getStatus().getLabel()=="AVAILABLE"){
                list.add("Room number:"+room.getRoomNumber()+"   Appartment:"+room.getApartment().getApartmentName());
            }
        }
        return list;
    }
    public List<String> getMonthList(){
        List<String> monthList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int monthValue=0;
        int year = currentDate.getYear();
        for (int i=0;i<3;i++){
            if ((currentMonth+i-monthValue)>12){
                monthValue+=12;
                year++;
            }
            Month month = Month.of(currentMonth+i-monthValue);
            String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
            monthList.add(year+"    "+monthName);
        }
        return monthList;
    }
    public LocalDate getStartMonth(String str){
        String[] arr = str.split("\\s+");
        int monthNumber = Month.valueOf(arr[1].toUpperCase()).getValue();
        int year = Integer.parseInt(arr[0]);
        return LocalDate.of(year,monthNumber,1);
    }
    public LocalDate getEnddMonth(LocalDate start , String str){
        double num = getTimeValue(str);
        return  start.plusMonths((int) num).minusDays(1);
    }
    public double getTimeValue(String str){
        switch (str){
            case "1 month":
                return 1;
            case "2 month":
                return 2;
            case "6 month":
                return 6;
            case "1 year":
                return 12;
            case "2 year":
                return 24;
            default:
                return 0;
        }
    }
    public void getTotal(double room,double time){
        double total = room*time;
        labelTotal.setText(total +"$");
    }
    public String buildId(){
        int number =0;
        if (contractDBGeneric.getAllData()!=null){
            number=contractDBGeneric.getAllData().size()+1;
        }
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "CT"+numberStr;
    }
    public void insertMonthly(LocalDate startMonth,LocalDate endMonth,Contract contract){
        while(startMonth.isBefore(endMonth)){
            Monthly monthly = new Monthly(1,contract,"Enter bill",LocalDate.of(startMonth.getYear(),startMonth.getMonth(),01));
            monthlyDBGeneric.insertData(monthly);
            startMonth=startMonth.plusMonths(1);
        }
    }
}
