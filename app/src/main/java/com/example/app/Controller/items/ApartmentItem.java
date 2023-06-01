package com.example.app.Controller.items;

import com.example.app.Controller.EditApartmentController;
import com.example.app.Controller.EditRoomController;
import com.example.app.Controller.LoginScene;
import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Apartment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApartmentItem implements Initializable {
    @FXML
    private Circle circleImage;

    @FXML
    private Label labelAddress;

    @FXML
    private Button labelDetail;

    @FXML
    private Label labelHost;

    @FXML
    private Label labelId;

    @FXML
    private Label labelName;

    @FXML
    private Label labelRoom;
    private Apartment apartment;

    public void setData(Apartment apartment){
        this.apartment=apartment;
        labelId.setText(apartment.getApartmentId());
        Image image = new Image(GetRootLink.getRootPath(apartment.getHost().getHostImage()).toString());
        circleImage.setFill(new ImagePattern(image));
        labelHost.setText(apartment.getHost().getHostName());
        labelName.setText(apartment.getApartmentName());
        labelAddress.setText(apartment.getAddress());
        labelRoom.setText("21");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelDetail.setOnAction(e->{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(LoginScene.getStage);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Edit/EditApartment.fxml"));
                //load scene
                AnchorPane sceneRoot = fxmlLoader.load();
                //get instance controller
                EditApartmentController item = fxmlLoader.getController();
                item.setDetail(apartment);
                //load scene
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Apartment details");
                dialogStage.showAndWait();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
