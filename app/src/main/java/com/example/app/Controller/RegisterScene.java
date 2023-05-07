package com.example.app.Controller;

import com.example.app.MyFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterScene implements Initializable {
@FXML
    Button linkLogin;
@FXML
    Pane viewPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkLogin.setOnAction(e->{
            System.out.println("get login");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/LoginScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
