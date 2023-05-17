package com.example.app.Controller.items;

import com.example.app.Entity.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RoomItem {
    @FXML
    private Label ClientName;

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
    public void setData(Room room){
        roomID.setText(room.getRoomId());
        number.setText(room.getRoomNumber());
        //set image

        //client info

        price.setText(String.valueOf(room.getPrice()));
        apartment.setText(room.getApartment().getApartmentName());

        switch (room.getStatus().getLabel()){
            case "AVAILABLE":
                status.setFill(Color.web("#38ff1f"));
                statusText.setText("AVAILABLE");
                break;
            case"OCCUPIED":
                status.setFill(Color.web("#ffc621"));
                statusText.setText("OCCUPIED");
                break;
            case "MAINTENANCE":
                status.setFill(Color.web("#ff2121"));
                statusText.setText("MAINTENANCE");
                break;
        }
    }
}
