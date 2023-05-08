package com.example.app.Controller;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.Entity.Validation;
import com.example.app.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterScene implements Initializable {
    @FXML
        Button linkLogin;
    @FXML
        Pane viewPane;

//get text field
    @FXML
    TextField emailForm;
    @FXML
    TextField nameForm;
    @FXML
    TextField passForm;
    @FXML
    TextField repassForm;
    @FXML
    Button confirmBtn;
    // validation text
    @FXML
    Label passwordVal;
    @FXML
    Label repassVal;
    @FXML
    Label nameVal;
    @FXML
    Label mailVal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkLogin.setOnAction(e->{
            backToLogin(e);
        });

        confirmBtn.setOnAction(e->{
            String email = emailForm.getText();
            String name = nameForm.getText();
            String pwd = passForm.getText();
            String repwd = repassForm.getText();
            //clear all validation notif
            passwordVal.setText("");
            repassVal.setText("");
            nameVal.setText("");
            mailVal.setText("");
            Validation validation = new Validation();
            boolean flag=true;
            // check password and repassword match
            if ( !pwd.equals(repwd)){
                flag=false;
                repassVal.setText("The password does not match");
            }
            // check length
            if (pwd.length()==0){
                flag=false;
                repassVal.setText("do not empty");
            }
            // check password
            if (!validation.isPwd(pwd)){
                flag=false;
                passwordVal.setText("Invalid password");
            }
            // check name
            if (!validation.isUserName(name) || name.length()==0){
                flag=false;
                nameVal.setText("Invalid name");
            }
            // check email
            if (!validation.isEmail(email) || email.length()==0){
                flag=false;
                mailVal.setText("Invalid email");
            }
            // check done
            if (flag){
                //buil id
                String userId=buildId();
                //hassing password
                String hashedPWd = BCrypt.hashpw(pwd,BCrypt.gensalt(12));
                User userAdd = new User(userId,email,name,hashedPWd);
                DBGeneric<User> userDBGeneric = new UserDB();
                // add user to db
                userDBGeneric.insertData(userAdd);
                backToLogin(e);
            }
        });

    }
    public void backToLogin(ActionEvent e){
        // back to login
        System.out.println("get back to login");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/LoginScene.fxml"));
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String buildId(){
        DBGeneric<User> userDBGeneric = new UserDB();
        int number=userDBGeneric.getAllData().size();
        String numberStr;
        if (number<10){
            numberStr="0"+number;
        }
        else {
            numberStr=Integer.toString(number);
        }
        return "USER"+numberStr;
    }
}
