package com.example.app.Controller;

import com.example.app.DB.ClientDAO;
import com.example.app.Entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
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
    private Button btnImg;

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
//        image = new Image(client.getClientImage());
        clientId.setText(client.getClientId());
        avatar.setImage(image);
        name.setText(client.getClientName());
        dob.setText(String.valueOf(client.getClientDOB()));
        phone.setText(String.valueOf(client.getClientPhone()));
        email.setText(client.getClientEmail());
        address.setText(client.getClientAddress());
        citizenID.setText(client.getClientId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {clientId, name, dob, phone, email, citizenID};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ClientDAO clientDAO = new ClientDAO();
//        Sự kiện khi click vào nút Edit sẽ cho phép sửa các ô input
        btnEdit.setOnMouseClicked(e->{
            for(TextField i : textFields){
                i.setEditable(true);
            }
            address.setEditable(true);
            btnImg.setDisable(false);
//            Sự kiện click vào nút chọn ảnh
            btnImg.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
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
                btnImg.setDisable(true);
            });
        });

//        Sự kiện khi click vào nút nạp lại
        btnRefresh.setOnMouseClicked(event->{

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
