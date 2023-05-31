package com.example.app.Controller.items;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.ContractDAO;
import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Client;
import com.example.app.Entity.Contract;
import com.example.app.Entity.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RoomItem {
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
    public void setData(Room room){
        roomID.setText(room.getRoomId());
        number.setText(room.getRoomNumber());

        //Client area
        if (room.getStatus().getLabel().equals("OCCUPIED")){
            Contract contract = new ContractDAO().searchContractByRoomId(room.getRoomId());
            System.out.println(contract);
            clientName.setText(contract.getClient().getClientName());
            clientImage.setFill(new ImagePattern(new Image(GetRootLink.getRootPathForClient(contract.getClient().getClientImage()).toString())));
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
}
