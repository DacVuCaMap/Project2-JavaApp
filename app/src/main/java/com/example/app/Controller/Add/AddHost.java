package com.example.app.Controller.Add;

import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddHost implements Initializable {
    @FXML
    private TextArea address;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField citizenID;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private TextField hostid;

    @FXML
    private TextField image;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //begin
        btnAdd.setOnAction(e->{
            //valiadation

            LocalDate date = dob.getValue();

            Host host = new Host(hostid.getText(),name.getText(),date,address.getText(),citizenID.getText(),image.getText(),email.getText(),phone.getText());
            DBGeneric<Host> hostDBGeneric = new HostDAO();
            hostDBGeneric.insertData(host);
        });
        }
}
