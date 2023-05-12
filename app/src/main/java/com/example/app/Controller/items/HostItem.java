package com.example.app.Controller.items;

import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class HostItem implements Initializable {
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

    public void setData(Host host){
        labelId.setText(host.getHostId());;
//        Image image = new Image(getClass().getResourceAsStream("/imageData/login.jpg"));
//        circleImage.setFill(new ImagePattern(image));;
<<<<<<< HEAD
        labelName.setText(host.getHostName());;
=======
        labelName.setText(host.getHostName());
>>>>>>> 354f44274b007986d8fdee3176743d72889cde95
        labelMail.setText(host.getHostEmail());
        labelPhone.setText(host.getHostPhone());
        labelAddress.setText(host.getAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
