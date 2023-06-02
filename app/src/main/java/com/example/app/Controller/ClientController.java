package com.example.app.Controller;

import com.example.app.Controller.items.ClientItem;
import com.example.app.Controller.items.RoomItem;
import com.example.app.DB.ClientDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Client;
import com.example.app.Entity.Room;
import com.example.app.Entity.User;
import com.example.app.Entity.sharedMenuData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    VBox vboxList;
    @FXML
    Button addBtn;
    @FXML
    TextField searchInput;
    @FXML
    Button searchBtn;
    private Stage primaryStage = LoginScene.getStage;
    private SelectMenuController selectMenuController;
    public void setSelectMenuController(SelectMenuController selectMenuController){
        this.selectMenuController = selectMenuController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVboxList();
        searchBtn.setOnAction(e->{
            updateSearch(searchInput.getText());
        });
    }
    public void setVboxList() {
        updateList();
        //click to add
        addBtn.setOnAction(e->{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Add/AddClient.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("My Dialog");
                dialogStage.showAndWait();
                //update head
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
        DBGeneric<Client> clientDBGeneric = new ClientDAO();
        List<Client> clientList = clientDBGeneric.getAllData();
        if (clientList!=null){
            for (Client client  : clientList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/ClientItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    ClientItem item = fxmlLoader.getController();
                    item.setData(client);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void updateSearch(String str){
        vboxList.getChildren().clear();
        List<Client> rs = new ArrayList<>();
        DBGeneric<Client> clientDBGeneric = new ClientDAO();
        List<Client> clientList = clientDBGeneric.getAllData();
        if (clientList!=null){
            for (Client client : clientList){
                if (client.getClientName().contains(str) || client.getClientId().contains(str)){
                    rs.add(client);
                }
            }
            for (Client client : rs){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/ClientItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    ClientItem item = fxmlLoader.getController();
                    item.setData(client);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
