package com.example.app.Controller;

import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class EditApartmentController implements Initializable {
    @FXML TextField hostName;
    @FXML TextField apartmentName;
    @FXML TextField address;
    @FXML TextField apId;
    @FXML private ImageView image;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Button btnImg;
    @FXML private Button btnRefresh;
    @FXML private Button btnSave;
    Image img =null;
    public void setDetail(Apartment ap){
        img = new Image(String.valueOf(getClass().getResource(ap.getApartmentImage())));
        apId.setText(ap.getApartmentId());
        apartmentName.setText(ap.getApartmentName());
        address.setText(ap.getAddress());
        image.setImage(img);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
