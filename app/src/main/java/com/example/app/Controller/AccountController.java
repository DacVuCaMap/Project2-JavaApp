package com.example.app.Controller;

import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.Entity.UserSession;
import com.example.app.Entity.Validation;
import com.example.app.Entity.sharedMenuData;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @FXML
    private TextField accEmail;

    @FXML
    private TextField accId;

    @FXML
    private TextField accName;

    @FXML
    private Button menuBtn;

    @FXML
    private Button nameBtn;

    @FXML
    private Button passwordBtn;

    @FXML
    private Label textImage;

    @FXML
    private Circle userImage;

    @FXML
    private Label userMail;

    @FXML
    private Label userName;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = new UserDB().getUserByMail(UserSession.userMail);
        getAvt();
        accId.setText(user.getUserId());
        accEmail.setText(user.getUserMail());
        accName.setText(user.getUserName());
        userName.setText(user.getUserName());
        userMail.setText(user.getUserMail());
        nameBtn.setOnAction(e->{
            //validation
            if (!Validation.isUserName(accName.getText())){
                 nameLabel.setText("* Invalid Name");
            }
            else {
                //update
                new UserDB().changeName(accName.getText(),user.getUserId());
                userName.setText(accName.getText());
                getAvt();
            }
        });
        passwordBtn.setOnAction(e->{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(passwordBtn.getScene().getWindow());
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/ChangePasswordScene.fxml"));
                AnchorPane sceneRoot = fxmlLoader.load();
                Scene dialogScene = new Scene(sceneRoot);
                dialogStage.setResizable(false);
                dialogStage.setScene(dialogScene);
                dialogStage.setTitle("Change password");
                dialogStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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
    public void getAvt(){
        int num = new Random().nextInt(3-1+1)+1;
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
