package com.example.app.Controller.Add;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Client;
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

public class AddClient implements Initializable {
    @FXML
    private TextArea address;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField citizenID;

    @FXML
    private TextField clientId;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private TextField image;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField roomID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setOnAction(e->{
            //valiadation

            LocalDate date = dob.getValue();

            Client client = new Client(clientId.getText(),image.getText(),name.getText(),email.getText(),phone.getText(),address.getText(),date,citizenID.getText());
            DBGeneric<Client> clientDBGeneric = new ClientDAO();
            clientDBGeneric.insertData(client);
        });
    }
}
