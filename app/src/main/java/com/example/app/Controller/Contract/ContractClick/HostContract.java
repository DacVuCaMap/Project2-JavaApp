package com.example.app.Controller.Contract.ContractClick;

import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Contract;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class HostContract {
    @FXML
    private Label address;

    @FXML
    private Circle circleImage;

    @FXML
    private Label citizenId;

    @FXML
    private Label hostEmail;

    @FXML
    private Label hostId;

    @FXML
    private Label hostName;

    @FXML
    private Label phoneNumber;
    @FXML
    private Label hostDob;

    @FXML
    private AnchorPane slideScene;
    public void setData(Contract contract){
        Host host = contract.getRoom().getApartment().getHost();
        hostId.setText(host.getHostId());
        hostName.setText(host.getHostName());
        hostDob.setText(host.getDob().toString());
        citizenId.setText(host.getCitizenId());
        hostEmail.setText(host.getHostEmail());
        phoneNumber.setText(host.getHostPhone());
        address.setText(host.getAddress());
        circleImage.setFill(new ImagePattern(new Image(GetRootLink.getRootPath(host.getHostImage()).toString())));
    }
}
