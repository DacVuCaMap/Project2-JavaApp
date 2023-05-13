package com.example.app.Controller;

import com.example.app.Controller.Add.AddHost;
import com.example.app.Controller.items.HostItem;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setVboxList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setVboxList() throws IOException {
        DBGeneric<Host> hostDBGeneric = new HostDAO();
        List<Host> hostList = hostDBGeneric.getAllData();
        for (Host host : hostList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/HostItem.fxml"));
            HBox hBox = fxmlLoader.load();
            HostItem item = fxmlLoader.getController();
            item.setData(host);
            vboxList.getChildren().add(hBox);
        }
        //click to add
        addBtn.setOnAction(e->{
            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/app/sceneView/Add/AddHost.fxml"));
            try{
                Pane addPane =loader.load();
                Scene newScene = new Scene(addPane);
                newStage.setScene(newScene);
                newStage.setTitle("Edit Category");
                newStage.show();
            }catch (IOException ee){
                ee.printStackTrace();
            }
        });
    }
}
