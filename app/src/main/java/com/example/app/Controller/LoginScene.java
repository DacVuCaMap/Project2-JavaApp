package com.example.app.Controller;

import com.example.app.DB.DBGeneric;
import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

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
    // check to login
    @FXML
    TextField emailForm;
    @FXML
    TextField pwdForm;
    @FXML
    Button confirmBtn;
    @FXML
    Label emailLable;
    @FXML
    Label pwdLable;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkRegister.setOnAction(e->{
            // to register
            System.out.println("get regis");
            Parent newScene = null;
            try {
                newScene = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/RegisterScene.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            leftSide.getChildren().setAll(newScene);
        });
        confirmBtn.setOnAction(e->{
            checkToLogin();
        });
        scenePane.setOnKeyPressed(keyEvent ->{
            if (keyEvent.getCode().toString().equals("ENTER")){
                checkToLogin();
            }
        });
    }
    public void checkToLogin(){
        String email = emailForm.getText();
        String pwd = pwdForm.getText();
        boolean flag=true;
        if (email.length()==0){
            flag=false;
            emailLable.setText("not empty");
        }
        if (pwd.length()==0){
            flag=false;
            pwdLable.setText("not empty");
        }
        if (flag){
            //check user
            User user = new User(null,email,null,pwd);
            DBGeneric<User> userDBGeneric = new UserDB();
            if (userDBGeneric.checkUser(user)){
                System.out.println("vaoo game");
                //
            }
        }
    }
}
