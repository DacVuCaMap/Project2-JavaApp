package com.example.app.Controller.items;

import com.example.app.Controller.Monthly.MonthlyDetailsScene;
import com.example.app.Controller.Monthly.MonthlyEnter;
import com.example.app.Data.WorkingFile;
import com.example.app.Entity.Monthly;
import com.example.app.Entity.MonthlyPrice;
import com.example.app.Entity.Room;
import com.example.app.Entity.sharedMenuData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MonthlyItem {
    @FXML
    private Label apartmentLabel;

    @FXML
    private Rectangle billStatus;

    @FXML
    private Label billText;

    @FXML
    private Label clientLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label numberLabel;
    private Stage stage;
    private boolean mode=true;
    private Monthly monthly;
    private Room room;
    public void setData(Monthly monthly){
        mode=true;
        this.monthly=monthly;
        numberLabel.setText("Room "+monthly.getContract().getRoom().getRoomNumber());
        idLabel.setText("ID: "+ monthly.getContract().getRoom().getRoomId());
        clientLabel.setText("Client: "+monthly.getContract().getRoom().getClient().getClientName());
        apartmentLabel.setText("Apartment: "+monthly.getContract().getRoom().getApartment().getApartmentName());
        billStatus.setFill(Color.RED);
        billText.setText("Enter Bill");
    }
    public void setData2(Room room){
        this.room=room;
        mode=false;
        numberLabel.setText("Room "+room.getRoomNumber());
        idLabel.setText("ID: "+ room.getRoomId());
        if (room.getClient()!=null){
            clientLabel.setText("Client: "+room.getClient().getClientName());
        }
        else{
            clientLabel.setText("CLient: empty");
        }
        apartmentLabel.setText("Apartment: "+room.getApartment().getApartmentName());
        billStatus.setFill(Color.GREEN);
        billText.setText("Details");
    }
    public void clickItem(MouseEvent e){
        Stage itemStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(itemStage);
        if (mode){
            MonthlyPrice monthlyPrice = WorkingFile.readMonthlyPrice();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/monthly/MonthlyEnter.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                MonthlyEnter item = fxmlLoader.getController();
                item.setData(monthly,monthlyPrice);
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Enter Price");
                dialogStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (!mode){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/monthly/MonthlyDetails.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                MonthlyDetailsScene item = fxmlLoader.getController();
                item.setData(room);
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Enter Price");
                dialogStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
