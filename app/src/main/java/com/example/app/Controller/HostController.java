package com.example.app.Controller;

import com.example.app.Controller.items.HostItem;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class HostController implements Initializable {
    @FXML
    VBox vboxList;

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
    }
}
