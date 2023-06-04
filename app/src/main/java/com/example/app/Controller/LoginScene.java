package com.example.app.Controller;

import com.example.app.DB.DBGeneric;
import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.Entity.UserSession;
import com.example.app.HelloApplication;
import com.example.app.MyFXMLLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
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
    private CheckBox rememberCheckBox;
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
    public static Stage getStage;
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
        scenePane.setOnKeyPressed(e ->{
            if (e.getCode().toString().equals("ENTER")){
                checkToLogin();
            }
        });

        //remember password
        Preferences prefs = Preferences.userNodeForPackage(LoginScene.class);
        String storedMail = prefs.get("userName","");
        String storedPassword = prefs.get("password","");
        if (!storedPassword.isEmpty() && !storedMail.isEmpty()){
            emailForm.setText(storedMail);
            pwdForm.setText(storedPassword);
            rememberCheckBox.setSelected(true);
        }
    }
    public void checkToLogin() {
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
                System.out.println("vaoo");
                // vao home
                try {
                    UserSession.userMail = user.getUserMail();
                    if (rememberCheckBox.isSelected()){
                        //store password
                        Preferences prefs = Preferences.userNodeForPackage(LoginScene.class);
                        prefs.put("userName",email);
                        prefs.put("password",pwd);
                    }else {
                        //clear the stored password
                        Preferences prefs = Preferences.userNodeForPackage(LoginScene.class);
                        prefs.remove("userName");
                        prefs.remove("password");
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/HomeScene.fxml"));
                    Scene scene = new Scene(root);
                    getStage.setScene(scene);
                    getStage.setTitle("Apartment Management");
                    getStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("sai mat khau");
            }
        }
    }
}
