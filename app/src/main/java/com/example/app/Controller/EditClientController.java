package com.example.app.Controller;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class EditClientController implements Initializable {
    @FXML
    private TextArea address;

    @FXML
    private ImageView avatar;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button changeImg;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnSave;
    @FXML
    private TextField citizenID;
    @FXML
    private TextField clientId;
    @FXML
    private TextField dob;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField room;
    Image image = null;
    public void setDetail(Client client){
        RoomDAO roomDAO = new RoomDAO();
        image = new Image(String.valueOf(getClass().getResource(client.getClientImage())));
        clientId.setText(client.getClientId());
        avatar.setImage(image);
        name.setText(client.getClientName());
        dob.setText(String.valueOf(client.getClientDOB()));
        phone.setText(String.valueOf(client.getClientPhone()));
        email.setText(client.getClientEmail());
        address.setText(client.getClientAddress());
        citizenID.setText(client.getCitizenID());
        room.setText(roomDAO.findByRoomId(client.getRoomId()).getRoomNumber());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {name, dob, phone, email, citizenID};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        ClientDAO clientDAO = new ClientDAO();
//        Sự kiện khi click vào nút Edit sẽ cho phép sửa các ô input
        btnEdit.setOnMouseClicked(e->{
            for(TextField i : textFields){
                i.setEditable(true);
            }
            address.setEditable(true);
            changeImg.setDisable(false);

//            Sự kiện click vào nút chọn ảnh
            changeImg.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    avatar.setImage(image);
                }});

//            Sự kiện khi chọn nút Save
            btnSave.setOnMouseClicked(event->{

                Client newClient = new Client(
                        clientId.getText(),
                        avatar.getImage().getUrl(),
                        name.getText(),
                        email.getText(),
                        phone.getText(),
                        address.getText(),
                        LocalDate.parse(dob.getText(), formatter),
                        citizenID.getText()
                );

                clientDAO.update(newClient, clientId.getText());
                for(TextField i: textFields){
                    i.setEditable(false);
                }
                address.setEditable(false);
                changeImg.setDisable(true);
            });
        });

//        Sự kiện khi click vào nút nạp lại
        btnRefresh.setOnMouseClicked(event->{
            Client client = new Client();
            client = clientDAO.getDataById(clientId.getText());
            image = new Image(String.valueOf(getClass().getResource(client.getClientImage())));
            clientId.setText(client.getClientId());
            avatar.setImage(image);
            name.setText(client.getClientName());
            dob.setText(String.valueOf(client.getClientDOB()));
            phone.setText(String.valueOf(client.getClientPhone()));
            email.setText(client.getClientEmail());
            address.setText(client.getClientAddress());
            citizenID.setText(client.getCitizenID());
            for(TextField i: textFields){
                i.setEditable(false);
            }
            address.setEditable(false);
            changeImg.setDisable(true);
        });

//        Sự kiện khi click vào nút delete
        btnDelete.setOnMouseClicked(event->{
            clientDAO.delete(clientId.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Xóa thành công");
            alert.setHeaderText(null);
            alert.setContentText("Bản ghi đã được xóa thành công.");
            alert.showAndWait();
            // Đóng cửa sổ hiện tại của bản ghi đó
            Stage stage = (Stage) btnDelete.getScene().getWindow();
            stage.close();
        });

    }
}
