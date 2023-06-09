package com.example.app.Controller.Monthly;

import com.example.app.Controller.items.MonthlyItem;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.MonthlyDAO;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Monthly;
import com.example.app.Entity.Room;
import com.example.app.Entity.sharedMenuData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlyScene implements Initializable {
    @FXML
    private VBox vboxMonthly;
    @FXML
    private Label nofLabel;
    @FXML
    private ChoiceBox<String> choiceTable;
    @FXML
    private Button configBtn;
    @FXML
    private Label timeLabel;

    @FXML
    private Label timeLeft;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sharedMenuData.monthlySceneController=this;
        MonthlyDAO monthlyDAO = new MonthlyDAO();
        DBGeneric<Room> roomDBGeneric = new RoomDAO();
        List<Room> roomList = roomDBGeneric.getAllData();
        List<Monthly> monthList=monthlyDAO.getDataByMonth(getMonth());
        System.out.println(getMonth());
        choiceTable.getItems().addAll("Data entry","Bill details");
        choiceTable.setValue("Data entry");
        if (monthList.size()>0){
            nofLabel.setVisible(false);
        }
            showData(monthList);
        choiceTable.setOnAction(e->{
            switch (choiceTable.getValue()){
                case "Data entry":
                    showData(monthList);
                    break;
                case "Bill details":
                    showDataDetails(roomList);
                    break;
            }
        });
    }
    public void showData(List<Monthly> monthlyList){
        vboxMonthly.getChildren().clear();
        for (int i=0;i<(monthlyList.size()/4)+1;i++){
            HBox hBox = new HBox();
            hBox.setPrefHeight(120);
            int count = i*4;
            for (int j=count;j<count+4;j++){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/MonthlyItem.fxml"));
                    try{
                        Pane pane = fxmlLoader.load();
                        MonthlyItem item = fxmlLoader.getController();
                        item.setData(monthlyList.get(j));
                        hBox.getChildren().add(pane);
                    }catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }catch (Exception ex){
                    break;
                }
            }
            vboxMonthly.getChildren().add(hBox);

        }
    }
    public void showDataDetails(List<Room> roomList){

        vboxMonthly.getChildren().clear();
        for (int i=0;i<(roomList.size()/4)+1;i++){
            HBox hBox = new HBox();
            hBox.setPrefHeight(120);
            int count = i*4;
            for (int j=count;j<count+4;j++){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/MonthlyItem.fxml"));
                    try{
                        Pane pane = fxmlLoader.load();
                        MonthlyItem item = fxmlLoader.getController();
                        item.setData2(roomList.get(j));
                        hBox.getChildren().add(pane);
                    }catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }catch (Exception ex){
                    break;
                }
            }
            vboxMonthly.getChildren().add(hBox);

        }
    }
    public LocalDate getMonth(){
        LocalDate currDate = LocalDate.now();
        if (currDate.getMonth().getValue()==12 && currDate.getDayOfMonth()>12){
            return LocalDate.of(currDate.getYear()+1,currDate.getMonth().plus(1),1);
        }
        if (currDate.getDayOfMonth()>12){
            return LocalDate.of(currDate.getYear(),currDate.getMonth().plus(1),1);
        }
        else{
            return LocalDate.of(currDate.getYear(),currDate.getMonth(),1);
        }
    }
    public void showDataPublic(){
        List<Monthly> monthlyList=new MonthlyDAO().getDataByMonth(getMonth());
        vboxMonthly.getChildren().clear();
        for (int i=0;i<(monthlyList.size()/4)+1;i++){
            HBox hBox = new HBox();
            hBox.setPrefHeight(120);
            int count = i*4;
            for (int j=count;j<count+4;j++){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/MonthlyItem.fxml"));
                    try{
                        Pane pane = fxmlLoader.load();
                        MonthlyItem item = fxmlLoader.getController();
                        item.setData(monthlyList.get(j));
                        hBox.getChildren().add(pane);
                    }catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }catch (Exception ex){
                    break;
                }
            }
            vboxMonthly.getChildren().add(hBox);

        }
    }
}
