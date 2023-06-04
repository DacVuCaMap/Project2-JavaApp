package com.example.app.Controller;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.UserDB;
import com.example.app.Entity.SendEmail;
import com.example.app.Entity.User;
import com.example.app.Entity.Validation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    @FXML
    Button confirmBtn2;
    @FXML
    Pane paneOTP;
    @FXML
    TextField num1;
    @FXML
    TextField num2;
    @FXML
    TextField num3;
    @FXML
    TextField num4;
    @FXML
    private String otp;
    @FXML
    private User userAdd;
    @FXML
    private Label labelVerify;
    @FXML
    private Label labelValid;
    @FXML
    private Label countdownLabel;
    private boolean flagSend=true;
    private int timeRemaining;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneOTP.setVisible(false);
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
            if (!new UserDB().checkMailUnique(email)){
                flag=false;
                mailVal.setText("* Already exist");
            }
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
                this.userAdd=new User(userId,email,name,hashedPWd);
                paneOTP.setVisible(true);
                num1.requestFocus();
                //buil otp code
                this.otp = SendEmail.sendMail(emailForm.getText());
                labelVerify.setText("We have sent an email to "+emailForm.getText());
                List<TextField> textFieldList = new ArrayList<>();
                textFieldList.addAll(Arrays.asList(num1, num2, num3, num4));
                changeTextField(textFieldList);
            }
        });
    confirmBtn2.setOnAction(e->{
        String checkCode=num1.getText()+num2.getText()+num3.getText()+num4.getText();
        if (otp.equals(checkCode)){
            DBGeneric<User> userDBGeneric = new UserDB();
            // add user to db
            userDBGeneric.insertData(userAdd);
            backToLogin(e);
        }
        else {
            labelValid.setText("* Invalid verify");
            num1.setText("");
            num2.setText("");
            num3.setText("");
            num4.setText("");
        }
    });
    }
    public void backToLogin(ActionEvent e){
        // back to loginscene
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
    public void changeTextField(List<TextField> list){
        for (int i=0;i<list.size();i++){
            final int currentIndex=i;
            System.out.println(currentIndex + " and " + list.size());
            list.get(currentIndex).textProperty().addListener((observable,oldValue,newValue)->{
                if (newValue.length()>0 && currentIndex!=list.size()-1){
                    list.get(currentIndex).setText(newValue.substring(0,1));//limit to one character
                    list.get(currentIndex+1).requestFocus();
                }
                if (newValue.length()>0 && currentIndex==list.size()-1){
                    list.get(currentIndex).setText(newValue.substring(0,1));
                    list.get(currentIndex).getParent().requestFocus();
                }
            });
        }
    }

    //resend email
    public void resendClick(MouseEvent e){
        //new build
        if (flagSend){
            countdownLabel.setText("Time remaining --:--");
            this.otp = SendEmail.sendMail(emailForm.getText());
            flagSend=false;
            this.timeRemaining = 120 ;
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (timeRemaining>0){
                        int minutes = timeRemaining / 60;
                        int seconds = timeRemaining % 60;
                        String timeString = String.format("Time remaining %02d:%02d", minutes, seconds);
                        Platform.runLater(() -> countdownLabel.setText(timeString));
                        timeRemaining--;
                    }
                    else {
                        flagSend=true;
                        timer.cancel();
                    }
                }
            },1000,1000);
        }
    }
}
