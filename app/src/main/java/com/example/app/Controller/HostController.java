package com.example.app.Controller;

import com.example.app.Controller.Add.AddHost;
import com.example.app.Controller.items.HostItem;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Host;
import com.example.app.Entity.sharedMenuData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
public class HostController implements Initializable {
    @FXML
    VBox vboxList;
    @FXML
    Button addBtn;
    @FXML
    Stage stage;
    private static Stage primaryStage = LoginScene.getStage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setVboxList(primaryStage);

    }
    public void setVboxList(Stage primaryStage) {
        this.primaryStage = primaryStage;
        updateList();
        //click to add
        addBtn.setOnAction(e->{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Add/AddHost.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("My Dialog");
                dialogStage.showAndWait();
                //update head bar
                sharedMenuData.getMenuController.headBarUpdate();
                //update list
                updateList();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void updateList(){
        vboxList.getChildren().clear();
        DBGeneric<Host> hostDBGeneric = new HostDAO();
        List<Host> hostList = hostDBGeneric.getAllData();
        if (hostList!=null){
            for (Host host : hostList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/HostItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    HostItem item = fxmlLoader.getController();
                    item.setData(host);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
