package com.example.app.Controller;

import com.example.app.Controller.items.ClientItem;
import com.example.app.DB.ClientDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.Entity.Client;
import com.example.app.Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    VBox vboxList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setVboxList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void setVboxList() throws IOException {
        DBGeneric<Client> clientDBGeneric = new ClientDAO();
//        List<Client> clientList = clientDBGeneric.getAllData();
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client("KH01","/imageData/login.jpg","Nam vu","namvu12@gmail.com"
                ,"0393623488","TP Ha Noi", LocalDate.of(2000,11,04),"1102",null));
        clientList.add(new Client("KH02","/imageData/login.jpg","Nam le","namvu32@gmail.com"
                ,"0123623488","TP cc Noi", LocalDate.of(2002,11,04),"123801923",null));
        for (Client client : clientList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/items/ClientItem.fxml"));
            HBox hBox = fxmlLoader.load();
            ClientItem item = fxmlLoader.getController();
            item.setData(client);
            vboxList.getChildren().add(hBox);
        }
    }
}
