package com.example.app.Controller.Contract;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContractMenu implements Initializable {
    @FXML
    private Button btnMenu;

    @FXML
    private Button listBtn;

    @FXML
    private AnchorPane mainScene;

    @FXML
    private Button revenueBtn;

    @FXML
    private AnchorPane slideScene;
    private Parent newScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listBtn.getStyleClass().add("selectedBtn");
        toList();
        listBtn.setOnAction(e->{
            listBtn.getStyleClass().add("selectedBtn");
            revenueBtn.getStyleClass().remove("selectedBtn");
            revenueBtn.getStyleClass().add("disableBtn");
            toList();
        });
        revenueBtn.setOnAction(e->{
            revenueBtn.getStyleClass().add("selectedBtn");
            listBtn.getStyleClass().remove("selectedBtn");
            listBtn.getStyleClass().add("disableBtn");
            toChart();
        });
        btnMenu.setOnAction(e->{
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
    public void toList(){
        try {
            newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/Contract/ContractListScene.fxml"));
            slideScene.getChildren().setAll(newScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void toChart(){
        try {
            newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/Contract/ContractChartScene.fxml"));
            slideScene.getChildren().setAll(newScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
