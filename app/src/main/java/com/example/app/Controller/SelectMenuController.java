package com.example.app.Controller;

import com.example.app.DB.*;
import com.example.app.Entity.User;
import com.example.app.Entity.UserSession;
import com.example.app.Entity.sharedMenuData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    @FXML
    Button btnMenu;
    @FXML
    private Label countClient;
    @FXML
    private Label countRoom;
    @FXML
    private Label countHost;
    @FXML
    private Label countApartment;
    @FXML
    private Circle userImg;
    @FXML
    private Label userName;
    @FXML
    private Label userMail;
    @FXML
    private Label characterUser;
    @FXML
    private Button monthlyBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // head user
        if (UserSession.userMail!=null){
            User user = new UserDB().getUserByMail(UserSession.userMail);
            userMail.setText(user.getUserMail());
            userName.setText(user.getUserName());
            characterUser.setText(user.getUserName().substring(0,1).toUpperCase());
        }

        sharedMenuData.getMenuController = this;
        headBarUpdate();
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
        monthlyBtn.setOnAction(e->{
            // diagram scene
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(LoginScene.getStage);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/monthly/MonthlyScene.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("MonthLy");
                dialogStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/app/sceneView/ClientScene.fxml"));
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
        monthlyBtn.getStyleClass().add("disableBtn");
    }
    public void selectedButton(Button btn){
        btn.getStyleClass().remove("disableBtn");
        btn.getStyleClass().add("selectedBtn");
    }
    public void headBarUpdate(){
        this.countClient.setText(String.valueOf(new ClientDAO().getAllData().size()));
        this.countRoom.setText(String.valueOf(new RoomDAO().getAllData().size()));
        this.countHost.setText(String.valueOf(new HostDAO().getAllData().size()));
        this.countApartment.setText(String.valueOf(new ApartmentDAO().getAllData().size()));
    }
}
