package com.example.app.Controller.items;

import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Apartment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ApartmentItem {
    @FXML
    private Circle circleImage;

    @FXML
    private Label labelAddress;

    @FXML
    private Button labelDetail;

    @FXML
    private Label labelHost;

    @FXML
    private Label labelId;

    @FXML
    private Label labelName;

    @FXML
    private Label labelRoom;

    public void setData(Apartment apartment){
        labelId.setText(apartment.getApartmentId());
        Image image = new Image(GetRootLink.getRootPath(apartment.getHost().getHostImage()).toString());
        circleImage.setFill(new ImagePattern(image));
        labelHost.setText(apartment.getHost().getHostName());
        labelName.setText(apartment.getApartmentName());
        labelAddress.setText(apartment.getAddress());
        labelRoom.setText("21");
    }
}
