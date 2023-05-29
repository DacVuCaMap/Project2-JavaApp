package com.example.app.Controller.Contract.ContractClick;

import com.example.app.Entity.Contract;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ApartmentContract {

    @FXML
    private Label apartmentAddressDetails;

    @FXML
    private Label apartmentIdDetails;

    @FXML
    private Label apartmentNameDetails;

    @FXML
    private AnchorPane slideScene;

    public void setData(Contract contract){
        apartmentIdDetails.setText(contract.getRoom().getApartment().getApartmentId());
        apartmentAddressDetails.setText(contract.getRoom().getApartment().getAddress());
        apartmentNameDetails.setText(contract.getRoom().getApartment().getApartmentName());
    }
}
