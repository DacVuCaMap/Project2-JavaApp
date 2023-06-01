package com.example.app.Controller.items;

import com.example.app.Controller.EditClientController;
import com.example.app.Controller.LoginScene;
import com.example.app.DB.GetRootLink;
import com.example.app.Entity.Client;
import com.example.app.Entity.sharedMenuData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientItem implements Initializable {
    @FXML
    private Circle circleImage;

    @FXML
    private Label labelAddress;

    @FXML
    private Button labelDetail;

    @FXML
    private Label labelId;

    @FXML
    private Label labelMail;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPhone;
    private Stage primaryStage = LoginScene.getStage;
    private Client client;
    public void setData(Client client){
        this.client=client;
        labelId.setText(client.getClientId());
        Image image = new Image(GetRootLink.getRootPathForClient(client.getClientImage()).toString());
        circleImage.setFill(new ImagePattern(image));
        labelName.setText(client.getClientName());
        labelMail.setText(client.getClientEmail());
        labelPhone.setText(client.getClientPhone());
        labelAddress.setText(client.getClientAddress());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelDetail.setOnAction(e->{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/Edit/EditClient.fxml"));
                //load scene
                AnchorPane sceneRoot = fxmlLoader.load();
                //get instance controller
                EditClientController editClientController = fxmlLoader.getController();
                editClientController.setDetail(this.client);
                //load scene
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Client details");
                dialogStage.showAndWait();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
