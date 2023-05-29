package com.example.app.Controller.Contract.ContractClick;

import com.example.app.Entity.Contract;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class RoomContract {
    @FXML
    private TextFlow description;

    @FXML
    private Label roomId;

    @FXML
    private Label roomNumber;

    @FXML
    private Label roomType;
    @FXML
    private Text textDes;
    @FXML
    private AnchorPane slideScene;
    public void setData(Contract contract){
        roomId.setText(contract.getRoom().getRoomId());
        roomNumber.setText(contract.getRoom().getRoomNumber());
        roomType.setText(contract.getRoom().getRoomType().getLabel());
        textDes.setText(contract.getRoom().getDesRoom());
    }
}
