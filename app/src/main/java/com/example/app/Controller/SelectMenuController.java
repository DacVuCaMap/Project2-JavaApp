package com.example.app.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectMenuController implements Initializable {
    @FXML
    Button clientBtn;
    @FXML
    Button roomBtn;
    @FXML
    AnchorPane slideScene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientBtn.setOnAction(e->{
            // to client
            System.out.println("get to client");
            Parent newScene = null;
            try {
                newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/ClientScene.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            slideScene.getChildren().setAll(newScene);
        });
        roomBtn.setOnAction(e->{
            //to room scene
            System.out.println("get to room");
            Parent newScene = null;
            try{
                newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/RoomScene.fxml"));

            }catch (IOException ex){
                throw new RuntimeException();
            }
            slideScene.getChildren().setAll(newScene);
        });
    }
}
