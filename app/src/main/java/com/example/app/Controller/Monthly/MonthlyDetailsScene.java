package com.example.app.Controller.Monthly;

import com.example.app.DB.MonthlyDetailsDAO;
import com.example.app.Entity.MonthlyDetails;
import com.example.app.Entity.Room;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlyDetailsScene implements Initializable {
    @FXML
    private Label apartmentName;

    @FXML
    private Label clientEmail;

    @FXML
    private Label clientName;

    @FXML
    private Label clientPhone;

    @FXML
    private Label createAt;

    @FXML
    private Label elecNumber;

    @FXML
    private Label elecPrice;

    @FXML
    private Label hostName;

    @FXML
    private Label hostPhone;

    @FXML
    private Label monthLabel;

    @FXML
    private Label roomNumber;

    @FXML
    private Label servicePrice;

    @FXML
    private VBox vboxMonth;

    @FXML
    private Label waterNumber;

    @FXML
    private Label waterPrice;
    @FXML
    private Label totalLabel;
    @FXML
    private TableView<MonthlyDetails> tableView;
    @FXML
    private TableColumn<MonthlyDetails,String>tableId;
    @FXML
    private TableColumn<MonthlyDetails,String>tableMonth;
    @FXML
    private Pane paneToHide;
    public void setData(Room room){
        List<MonthlyDetails> list = new MonthlyDetailsDAO().getByRoom(room.getRoomId());
        ObservableList<MonthlyDetails> observableList = FXCollections.observableList(list);
        tableId.setCellValueFactory(mon-> new SimpleStringProperty(mon.getValue().getId()));
        tableMonth.setCellValueFactory(mon->new SimpleStringProperty(mon.getValue().getMonth_year()));
        tableView.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setOnMouseClicked(e->{
            MonthlyDetails monthlyDetails = new MonthlyDetailsDAO().getById(tableView.getSelectionModel().getSelectedItem().getId());
            if (monthlyDetails!=null){
                paneToHide.setVisible(false);
                createAt.setText("Create at: "+monthlyDetails.getCreate_at().toString());
                monthLabel.setText(monthlyDetails.getMonth_year());
                clientName.setText("Client Name: "+monthlyDetails.getClient().getClientName());
                clientPhone.setText("Client phone: "+monthlyDetails.getClient().getClientPhone());
                clientEmail.setText("Client email: "+monthlyDetails.getClient().getClientEmail());
                hostName.setText("Host name: "+monthlyDetails.getRoom().getApartment().getHost().getHostName());
                hostPhone.setText("Host phone: "+monthlyDetails.getRoom().getApartment().getHost().getHostPhone());
                apartmentName.setText("Apartment Name: "+monthlyDetails.getRoom().getApartment().getApartmentName());
                roomNumber.setText("Room number: "+monthlyDetails.getRoom().getRoomNumber());
                elecPrice.setText("Electric price: "+String.valueOf(monthlyDetails.getElectricPrice()));
                elecNumber.setText("Electric number: "+String.valueOf(monthlyDetails.getElectricNumber()));
                waterPrice.setText("Water price: "+String.valueOf(monthlyDetails.getWaterPrice()));
                waterNumber.setText("Water number: "+String.valueOf(monthlyDetails.getWaterNumber()));
                servicePrice.setText("Service price: "+String.valueOf(monthlyDetails.getServicePrice()));
                Double total = monthlyDetails.getElectricPrice()*monthlyDetails.getElectricNumber()+monthlyDetails.getWaterPrice()*monthlyDetails.getWaterNumber()+monthlyDetails.getServicePrice();
                totalLabel.setText("Total: "+total);
            }
        });
    }
}
