package com.example.app.Controller.Add;

import com.example.app.DB.ApartmentDAO;
import com.example.app.DB.ClientDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddApartment implements Initializable {
    @FXML
    private TextArea address;

    @FXML
    private TextField apartmentId;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField hostId;

    @FXML
    private TextField image;

    @FXML
    private TextField name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setOnAction(e->{

        });
    }
}
