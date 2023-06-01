package com.example.app.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManualController implements Initializable {
    @FXML private Button menuBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuBtn.setOnAction(e->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/HomeScene.fxml"));
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
