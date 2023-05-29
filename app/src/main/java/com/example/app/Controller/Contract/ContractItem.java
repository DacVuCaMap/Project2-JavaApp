package com.example.app.Controller.Contract;

import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Contract;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

public class ContractItem {
    @FXML
    private Circle circleImage;

    @FXML
    private Label labelId;

    @FXML
    private Label labelName;

    @FXML
    private Label labelRoom;

    @FXML
    private Label labelTime;

    @FXML
    private Label labelTotal;
    @FXML
    private Label labelTime2;
    private Contract contract;
    public void setData(Contract contract){
        this.contract = contract;
        circleImage.setFill(new ImagePattern(new Image(GetRootLink.getRootPathForClient(contract.getClient().getClientImage()).toString())));
        labelId.setText(contract.getContractId());
        labelName.setText(contract.getClient().getClientName());
        labelRoom.setText(contract.getRoom().getRoomId());
        labelTime.setText(contract.getStartDate().toString());
        labelTotal.setText(contract.getTotalPrice()+"$");
        long monthNum = ChronoUnit.MONTHS.between(contract.getStartMonth(),contract.getEndMonth());
        labelTime2.setText((monthNum+1)+" month");
    }
    public void getClick(MouseEvent event){
        Stage dialogStage = new Stage();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/ContractDetailsScene.fxml"));
            AnchorPane screenRoot = fxmlLoader.load();
            Scene dialogScene = new Scene(screenRoot);
            ContractDetails contractDetails = fxmlLoader.getController();
            contractDetails.setData(contract);
            dialogStage.setResizable(false);
            dialogStage.setScene(dialogScene);
            dialogStage.setTitle("Contract detail");
            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
