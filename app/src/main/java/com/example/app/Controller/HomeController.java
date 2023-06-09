package com.example.app.Controller;


import com.example.app.DB.MySQLConnection;
import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.Entity.UserSession;
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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btnAboutUs;

    @FXML
    private Button btnAccount;

    @FXML
    private Button btnContract;

    @FXML
    private Button btnDocs;

    @FXML
    private Button btnManagement;

    @FXML
    private Button btnSetting;

    @FXML
    private Label textImage;

    @FXML
    private Circle userImage;

    @FXML
    private Label userMail;

    @FXML
    private Label userName;
    @FXML
    private Button logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
        btnManagement.setOnAction(e->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/SelectMenuScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnContract.setOnAction(e->{
            btnContract.getScene().setCursor(Cursor.WAIT);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/Contract/ContractMenuScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                btnContract.getScene().setCursor(Cursor.DEFAULT);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnAccount.setOnAction(e->{
            setStage("AccountScene.fxml",e);
        });
        btnSetting.setOnAction(e->{

        });
        btnAboutUs.setOnAction(e->{
            setStage("AboutUsScene.fxml",e);
        });
        btnDocs.setOnAction(e->{
            setStage("UserManualScene.fxml",e);
        });
        logoutBtn.setOnAction(e->{
            UserSession.userMail="";
            setStage("LoginScene.fxml",e);
        });
    }
    public void setStage(String str,ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/"+str));
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void setText(){
        User user = new UserDB().getUserByMail(UserSession.userMail);
        if (user!=null){
            userName.setText(user.getUserName());
            userMail.setText(user.getUserMail());
            textImage.setText(user.getUserName().substring(0,1));
            int num = new Random().nextInt(3-1+1)+1;
            System.out.println(num);
            getRandomAvt(num);
        }
    }
    public void getRandomAvt(int num){
        switch (num){
            case 1:
                userImage.setFill(Color.web("#8b0000"));
                textImage.setTextFill(Color.WHITE);
                break;
            case 2:
                userImage.setFill(Color.web("#48d1cc"));
                textImage.setTextFill(Color.BLACK);
                break;
            case 3:
                userImage.setFill(Color.web("#adff2f"));
                textImage.setTextFill(Color.BLACK);
        }
    }
}


