package com.example.app.Controller.Add;
import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import javafx.event.ActionEvent;

import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddHost implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    private TextField citizenId;

    @FXML
    private DatePicker hostDob;

    @FXML
    private TextField hostMail;

    @FXML
    private TextField hostName;

    @FXML
    private TextField hostPhone;

    @FXML
    private TextField hostaddress;

    @FXML
    private Button imgBtn;
    private static DBGeneric<Host> hostDBGeneric = new HostDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add to database
        addBtn.setOnAction(e->{
            // validation

            //get insert
            String hostId = buildId();
            String imgLink = "/imageData/login.jpg";
            Host host = new Host(hostId,hostName.getText()
            ,hostDob.getValue(),hostaddress.getText(),citizenId.getText(),imgLink,hostMail.getText(),hostPhone.getText());
            hostDBGeneric.insertData(host);
        });
        cancleBtn.setOnAction(e->{
            // Get the current stage
            Stage stage = (Stage) cancleBtn.getScene().getWindow();

            // Close the stage
            stage.close();
        });
        imgBtn.setOnAction(e->{
            Stage stage = (Stage) imgBtn.getScene().getWindow();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF File","*pdf"));
            File file = fc.showOpenDialog(stage);
        });
    }
    public static String buildId(){
        int number=hostDBGeneric.getAllData().size()+1;
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "HT"+numberStr;
    }
}
