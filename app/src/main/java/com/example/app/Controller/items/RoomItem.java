package com.example.app.Controller.items;

import com.example.app.Controller.EditClientController;
import com.example.app.Controller.EditRoomController;
import com.example.app.Controller.LoginScene;
import com.example.app.DB.ClientDAO;
import com.example.app.DB.ContractDAO;
import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Client;
import com.example.app.Entity.Contract;
import com.example.app.Entity.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomItem implements Initializable {
    @FXML
    private Label clientName;

    @FXML
    private Label apartment;

    @FXML
    private Circle clientImage;

    @FXML
    private Button labelDetail;

    @FXML
    private Label number;

    @FXML
    private Label price;

    @FXML
    private Label roomID;

    @FXML
    private Rectangle status;
    @FXML
    private Label statusText;
    private Room room;
    public void setData(Room room){
        this.room=room;
        roomID.setText(room.getRoomId());
        number.setText(room.getRoomNumber());
        System.out.println(room.getStatus().getLabel().equals("OCCUPIED"));
        System.out.println(room.getClient());
        //Client area
        if (room.getStatus().getLabel().equals("OCCUPIED")){
            clientName.setText(room.getClient().getClientName());
            clientImage.setFill(new ImagePattern(new Image(GetRootLink.getRootPathForClient(room.getClient().getClientImage()).toString())));
        }
        else {
            clientName.setText("Empty");
            clientImage.setVisible(false);
        }
        price.setText(room.getPrice()+" $");
        apartment.setText(room.getApartment().getApartmentName());

        switch (room.getStatus().getLabel()){
            case "OCCUPIED":
                status.setFill(Color.web("#ffc621"));
                statusText.setText("OCCUPIED");
                break;
            case"MAINTENANCE":
                status.setFill(Color.web("#ff2121"));
                statusText.setText("MAINTENANCE");
                break;
            case "AVAILABLE":
                status.setFill(Color.web("#38ff1f"));
                statusText.setText("AVAILABLE");
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelDetail.setOnAction(e->{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(LoginScene.getStage);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Edit/EditRoom.fxml"));
                //load scene
                AnchorPane sceneRoot = fxmlLoader.load();
                //get instance controller
                EditRoomController item = fxmlLoader.getController();
                item.setDetail(room);
                //load scene
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Room details");
                dialogStage.showAndWait();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
