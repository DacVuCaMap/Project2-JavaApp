package com.example.app.Controller.Contract.ContractClick;

import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Client;
import com.example.app.Entity.Contract;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ClientContract {
    @FXML
    private Label address;

    @FXML
    private Circle circleImage;

    @FXML
    private Label citizenId;

    @FXML
    private Label clientDob;

    @FXML
    private Label clientEmail;

    @FXML
    private Label clientId;

    @FXML
    private Label clientName;

    @FXML
    private Label phoneNumber;

    @FXML
    private AnchorPane slideScene;
    public void setData(Contract contract){
        Client client = contract.getClient();
        clientName.setText(client.getClientName());
        clientId.setText(client.getClientId());
        clientDob.setText(client.getClientDOB().toString());
        citizenId.setText(client.getCitizenID());
        clientEmail.setText(client.getClientEmail());
        phoneNumber.setText(client.getClientPhone());
        address.setText(client.getClientAddress());
        circleImage.setFill(new ImagePattern(new Image(GetRootLink.getRootPathForClient(client.getClientImage()).toString())));
    }
}
