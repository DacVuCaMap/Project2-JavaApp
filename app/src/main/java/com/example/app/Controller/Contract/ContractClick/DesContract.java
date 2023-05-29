package com.example.app.Controller.Contract.ContractClick;

import com.example.app.Entity.Contract;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class DesContract {

    @FXML
    private AnchorPane slideScene;

    @FXML
    private Text textDes;
    public void setData(Contract contract){
        textDes.setText(contract.getDescription());
    }
}
