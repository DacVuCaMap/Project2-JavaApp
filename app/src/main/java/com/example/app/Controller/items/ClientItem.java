package com.example.app.Controller.items;

import com.example.app.Entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientItem implements Initializable {
    @FXML
    private Circle circleImage;

    @FXML
    private Label labelAddress;

    @FXML
    private Button labelDetail;

    @FXML
    private Label labelId;

    @FXML
    private Label labelMail;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPhone;

    public void setData(Client client){
        labelId.setText(client.getClientId());
        Image image = new Image(getClass().getResourceAsStream(client.getClientImage()));
        circleImage.setFill(new ImagePattern(image));
        labelName.setText(client.getClientName());
        labelMail.setText(client.getClientEmail());
        labelPhone.setText(client.getClientPhone());
        labelAddress.setText(client.getClientAddress());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
