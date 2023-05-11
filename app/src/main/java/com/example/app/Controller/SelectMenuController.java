package com.example.app.Controller;

import javafx.event.ActionEvent;
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
    AnchorPane mainScene;
    @FXML
    Button clientBtn;
    @FXML
    Button roomBtn;
    @FXML
    AnchorPane slideScene;
    // css btn
    @FXML
    Button hostBtn;
    @FXML
    Button apartmentBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // mac dinh vao client
        clearAllBtn();
        toClient();
        clientBtn.getStyleClass().add("selectedBtn");
        //click client
        clientBtn.setOnAction(e->{
            // to client
            clearAllBtn();
            selectedButton(clientBtn);
            toClient();
        });
        //click room
        roomBtn.setOnAction(e->{
            toRoom();
        });
        //click host
        hostBtn.setOnAction(e->{
            toHost();
        });
        //click am
        apartmentBtn.setOnAction(e->{
            toAM();
        });
    }
    public void toAM(){
        //change css btn
        clearAllBtn();
        selectedButton(apartmentBtn);
        //to apartment scene
        System.out.println("get to apartment");
        Parent newScene ;
        try{
            newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/ApartmentScene.fxml"));

        }catch (IOException ex){
            throw new RuntimeException();
        }
        slideScene.getChildren().setAll(newScene);
    }
    public void toHost(){
        //change css btn
        clearAllBtn();
        selectedButton(hostBtn);
        //to host scene
        System.out.println("get to host");
        Parent newScene ;
        try{
            newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/HostScene.fxml"));

        }catch (IOException ex){
            throw new RuntimeException();
        }
        slideScene.getChildren().setAll(newScene);
    }
    public void toRoom(){
        //change css btn
        clearAllBtn();
        selectedButton(roomBtn);
        //to room scene
        System.out.println("get to room");
        Parent newScene ;
        try{
            newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/RoomScene.fxml"));

        }catch (IOException ex){
            throw new RuntimeException();
        }
        slideScene.getChildren().setAll(newScene);
    }
    public void toClient(){
        System.out.println("get to client");
        Parent newScene ;
        try {
            newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/ClientScene.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        slideScene.getChildren().setAll(newScene);
    }
    public void clearAllBtn(){
        clientBtn.getStyleClass().remove("selectedBtn");
        roomBtn.getStyleClass().remove("selectedBtn");
        hostBtn.getStyleClass().remove("selectedBtn");
        apartmentBtn.getStyleClass().remove("selectedBtn");
        clientBtn.getStyleClass().add("disableBtn");
        roomBtn.getStyleClass().add("disableBtn");
        hostBtn.getStyleClass().add("disableBtn");
        apartmentBtn.getStyleClass().add("disableBtn");
    }
    public void selectedButton(Button btn){
        btn.getStyleClass().remove("disableBtn");
        btn.getStyleClass().add("selectedBtn");
    }
}
