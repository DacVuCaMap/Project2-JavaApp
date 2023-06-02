package com.example.app.Controller;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.GetRootLink;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Client;
import com.example.app.FormatDate;
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
import java.nio.file.Paths;
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
    @FXML Label tmpURL;
    Image image = null;
    public void setDetail(Client client){
        RoomDAO roomDAO = new RoomDAO();
        image = new Image(GetRootLink.getRootPathForClient(client.getClientImage()).toString());
        clientId.setText(client.getClientId());
        avatar.setImage(image);
        name.setText(client.getClientName());
        dob.setText(FormatDate.formatDateString(String.valueOf(client.getClientDOB())));
        phone.setText(String.valueOf(client.getClientPhone()));
        email.setText(client.getClientEmail());
        address.setText(client.getClientAddress());
        citizenID.setText(client.getCitizenID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {name, dob, phone, email, citizenID};
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
                    tmpURL.setText(selectedFile.toString());
                    System.out.println(selectedFile.toString());
                }});

//            Sự kiện khi chọn nút Save
            btnSave.setOnMouseClicked(event->{
                if(tmpURL.getText()==""){
                    Client clientUpdate = new Client(
                            clientId.getText(),
                            name.getText(),
                            email.getText(),
                            phone.getText(),
                            address.getText(),
                            LocalDate.parse(FormatDate.formatDateReverse(dob.getText())),
                            citizenID.getText()
                    );
                   clientDAO.updateNoImg(clientUpdate, clientId.getText());
                }else {
                    String absolute = tmpURL.getText();
                    Path absolutePath = Path.of(absolute);
                    Path destination = Path.of(System.getProperty("user.dir"), "src/main/resources/imageData/objectData/clientIMG");
                    try {
                        Files.copy(absolutePath, destination.resolve(absolutePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Path path = Paths.get(absolute);
                    String fileName = path.getFileName().toString();

                    Client newClient = new Client(
                            clientId.getText(),
                            fileName,
                            name.getText(),
                            email.getText(),
                            phone.getText(),
                            address.getText(),
                            LocalDate.parse(FormatDate.formatDateReverse(dob.getText())),
                            citizenID.getText()
                    );

                    clientDAO.update(newClient, clientId.getText());
                }
                tmpURL.setText("");
                for(TextField i: textFields){
                    i.setEditable(false);
                }
                address.setEditable(false);
                changeImg.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit success");
                alert.setHeaderText(null);
                alert.setContentText("Your update is complete!");
                alert.showAndWait();
            });
        });

//        Sự kiện khi click vào nút nạp lại
        btnRefresh.setOnMouseClicked(event->{
            Client client = new Client();
            client = clientDAO.getDataById(clientId.getText());
            image = new Image(GetRootLink.getRootPathForClient(client.getClientImage()).toString());
            clientId.setText(client.getClientId());
            avatar.setImage(image);
            name.setText(client.getClientName());
            dob.setText(FormatDate.formatDateString(String.valueOf(client.getClientDOB())));
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
//            clientDAO.delete(clientId.getText());
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Xóa thành công");
//            alert.setHeaderText(null);
//            alert.setContentText("Bản ghi đã được xóa thành công.");
//            alert.showAndWait();
//            // Đóng cửa sổ hiện tại của bản ghi đó
//            Stage stage = (Stage) btnDelete.getScene().getWindow();
//            stage.close();
        });

    }
}
