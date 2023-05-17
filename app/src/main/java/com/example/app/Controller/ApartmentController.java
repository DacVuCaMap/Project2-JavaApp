package com.example.app.Controller;

import com.example.app.Controller.items.ApartmentItem;
import com.example.app.Controller.items.HostItem;
import com.example.app.DB.ApartmentDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ApartmentController implements Initializable {
    private static Stage primaryStage = LoginScene.getStage;
    @FXML
    Button addBtn;
    @FXML
    VBox vboxList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVboxList();
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
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Add/AddApartment.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("My Dialog");
                dialogStage.showAndWait();
                //update list
                updateList();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    public void updateList(){
        vboxList.getChildren().clear();
        DBGeneric<Apartment> apartmentDBGeneric = new ApartmentDAO();
        List<Apartment> apartmentList = apartmentDBGeneric.getAllData();
        if (apartmentList!=null){
            for (Apartment apartment : apartmentList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/ApartmentItem.fxml"));
                try{
                    HBox hBox = fxmlLoader.load();
                    ApartmentItem item = fxmlLoader.getController();
                    item.setData(apartment);
                    vboxList.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
