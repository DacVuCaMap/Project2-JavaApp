package com.example.app.Controller;

import com.example.app.Controller.items.ApartmentItem;
import com.example.app.Controller.items.ClientItem;
import com.example.app.Controller.items.RoomItem;
import com.example.app.DB.ApartmentDAO;
import com.example.app.DB.ClientDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
import com.example.app.Entity.Room;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private VBox vboxList;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchInput;
    private Stage primaryStage = LoginScene.getStage;

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
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Add/AddRoom.fxml"));
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
        DBGeneric<Room> roomDBGeneric = new RoomDAO();
        List<Room> roomList = roomDBGeneric.getAllData();
        if (roomList!=null){
            for (Room room : roomList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/RoomItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    RoomItem item = fxmlLoader.getController();
                    item.setData(room);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void updateSearch(String str){
        vboxList.getChildren().clear();
        List<Room> rs = new ArrayList<>();
        DBGeneric<Room> roomDBGeneric = new RoomDAO();
        List<Room> roomList = roomDBGeneric.getAllData();
        if (roomList!=null){
            for (Room room : roomList){
                if (room.getRoomId().contains(str) || room.getRoomNumber().contains(str)){
                    rs.add(room);
                }
            }
            for (Room room : rs){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/RoomItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    RoomItem item = fxmlLoader.getController();
                    item.setData(room);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
