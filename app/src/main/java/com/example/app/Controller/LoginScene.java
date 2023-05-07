package com.example.app.Controller;

import com.example.app.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScene implements Initializable {
    @FXML
    AnchorPane scenePane;
    @FXML
    AnchorPane leftSide;
    @FXML
    BorderPane borderPane;
    Stage stage;
    @FXML
    Button linkRegister;
    @FXML
    Pane viewPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkRegister.setOnAction(e->{
            System.out.println("get regis");
            /*viewPane = MyFXMLLoader.getView("sceneView/RegisterScene.fxml");
            borderPane.setCenter(viewPane);*/
            Parent newScene = null;
            try {
                newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/RegisterScene.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            leftSide.getChildren().setAll(newScene);
        });
    }
}
