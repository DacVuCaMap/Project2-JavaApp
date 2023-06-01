package com.example.app.Controller;

import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.Entity.UserSession;
import com.example.app.Entity.Validation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassword implements Initializable {
    @FXML
    private TextField pwdField;
    @FXML
    private Button cancleBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private PasswordField newField;

    @FXML
    private Label newLabel;

    @FXML
    private Label pwdLabel;

    @FXML
    private PasswordField reField;

    @FXML
    private Label reLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = new UserDB().getUserByMail(UserSession.userMail);
        confirmBtn.setOnAction(e->{
            //validation
            boolean flag = true;
            if (!BCrypt.checkpw(pwdField.getText(),user.getPwd())){
                flag=false;
                pwdLabel.setText("* wrong password");
            }
            if (!Validation.isPwd(newField.getText())){
                flag=false;
                newLabel.setText("* Invalid password");
            }
            System.out.println(newField + " and "+reField);
            if (!newField.getText().equals(reField.getText())){
                flag=false;
                reLabel.setText("* Invalid");
            }
            if (flag){
                //hash password
                String pwd = BCrypt.hashpw(newField.getText(),BCrypt.gensalt(12));
                new UserDB().changePassword(pwd,user.getUserId());
                Stage stage = (Stage) cancleBtn.getScene().getWindow();
                stage.close();
            }
        });
        cancleBtn.setOnAction(e->{
            Stage stage = (Stage) cancleBtn.getScene().getWindow();
            stage.close();
        });
    }
}
