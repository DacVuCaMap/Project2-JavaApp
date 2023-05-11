package com.example.app.Controller;

import com.example.app.Entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditClientController implements Initializable {
    @FXML TextField name;
    @FXML TextField dob;
    @FXML TextField phone;
    @FXML TextField email;
    @FXML TextField citizenID;
    @FXML TextField room;
    @FXML TextArea address;
    @FXML TextField clientId;
    @FXML Button btnEdit;
    @FXML Button btnSave;
    @FXML Button btnDelete;
    @FXML Button btnRefresh;
    TextField[] textFieldArray = {clientId, name, dob, phone, email, citizenID};
    public void setDetail(Client client){
        clientId.setText(client.getClientId());
        name.setText(client.getClientName());
        dob.setText(String.valueOf(client.getClientDOB()));
        phone.setText(String.valueOf(client.getClientPhone()));
        email.setText(client.getClientEmail());
        address.setText(client.getClientAddress());
        citizenID.setText(client.getClientId());
        room.setText(String.valueOf(client.getRoom().getRoomNumber()));

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setOnMouseClicked(e->{
            name.setEditable(true);
            dob.setEditable(true);
            phone.setEditable(true);
            email.setEditable(true);
            address.setEditable(true);
            citizenID.setEditable(true);
            AtomicBoolean isChange = new AtomicBoolean(false);
            for(TextField i : textFieldArray){
                if(i.getText().equals(i.))
            }


        });
    }
}
