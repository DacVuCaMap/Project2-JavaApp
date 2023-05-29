package com.example.app.Controller.Contract;

import com.example.app.Controller.Contract.ContractClick.*;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Contract;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContractDetails implements Initializable {
    @FXML
    private Label apartmentId;

    @FXML
    private Label clientId;

    @FXML
    private Label des;

    @FXML
    private Label hostId;

    @FXML
    private Label labelContract;

    @FXML
    private Label roomId;

    @FXML
    private AnchorPane slideScene;
    @FXML
    private Label apartmentAddressDetails;

    @FXML
    private Label apartmentIdDetails;

    @FXML
    private Label apartmentNameDetails;
    @FXML
    private Label creatAtLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Parent newScene;
    private Contract contract;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setData(Contract contract){
        this.contract = contract;
        labelContract.setText("Contract-"+contract.getContractId());
        clientId.setText(contract.getClient().getClientId()+"   >");
        hostId.setText(contract.getRoom().getApartment().getHost().getHostId()+"   >");
        apartmentId.setText(contract.getRoom().getApartment().getApartmentId()+"   >");
        roomId.setText(contract.getRoom().getRoomId()+"   >");
        startDateLabel.setText(contract.getStartMonth().toString());
        endDateLabel.setText(contract.getEndMonth().toString());
        creatAtLabel.setText(contract.getStartDate().toString());
    }
    @FXML
    void getApartment(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/ApartmentContract.fxml"));
            newScene = fxmlLoader.load();
            ApartmentContract apartmentContract = fxmlLoader.getController();
            apartmentContract.setData(contract);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        slideScene.getChildren().setAll(newScene);
    }

    @FXML
    void getClient(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/ClientContract.fxml"));
            newScene = fxmlLoader.load();
            ClientContract item = fxmlLoader.getController();
            item.setData(contract);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        slideScene.getChildren().setAll(newScene);
    }

    @FXML
    void getDes(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/desContract.fxml"));
            newScene = fxmlLoader.load();
            DesContract item = fxmlLoader.getController();
            item.setData(contract);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        slideScene.getChildren().setAll(newScene);
    }

    @FXML
    void getHost(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/HostContract.fxml"));
            newScene = fxmlLoader.load();
            HostContract item = fxmlLoader.getController();
            item.setData(contract);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        slideScene.getChildren().setAll(newScene);
    }

    @FXML
    void getRoom(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/RoomContract.fxml"));
            newScene = fxmlLoader.load();
            RoomContract item = fxmlLoader.getController();
            item.setData(contract);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        slideScene.getChildren().setAll(newScene);
    }
}
