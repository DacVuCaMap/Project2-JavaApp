package com.example.app.Controller.Contract;

import com.example.app.DB.ContractDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.Entity.Contract;
import com.example.app.Entity.sharedMenuData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContractList implements Initializable {
    @FXML
    private VBox vboxList;
    @FXML
    private Button btnContract;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sharedMenuData.contractListController = this;
        upDateList();

        btnContract.setOnAction(e->{
            Stage dialogStage = new Stage();
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/AddContractScene.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Contract");
                dialogStage.showAndWait();
                upDateList();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void upDateList(){
        vboxList.getChildren().clear();
        DBGeneric<Contract> contractDBGeneric = new ContractDAO();
        List<Contract> contractList = contractDBGeneric.getAllData();
        if (contractList!=null){
            for (Contract contract : contractList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Contract/ContractItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    ContractItem contractItem = fxmlLoader.getController();
                    contractItem.setData(contract);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
