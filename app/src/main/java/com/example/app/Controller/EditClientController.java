package com.example.app.Controller;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.GetRootLink;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Client;
import com.example.app.Entity.Validation;
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
import java.util.Optional;
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
                boolean flag = true;
                //validation
                if (!Validation.isEmail(email.getText())){
                    flag = false;
                    email.setText("* Invalid email");
                    email.setStyle("-fx-text-fill: red;");
                }
                //room chua co
                if (!Validation.isUserName(name.getText())){
                    flag=false;
                    name.setText("* Invalid name");
                    name.setStyle("-fx-text-fill: red;");
                }

                if (!Validation.isNbr(phone.getText()) || phone.getText().length()<10){
                    flag=false;
                    phone.setText("* Invalid phone");
                    phone.setStyle("-fx-text-fill: red;");
                }
                if (dob.getText() == null){
                    flag= false;
                    dob.setText("* Not empty");
                    dob.setStyle("-fx-text-fill: red;");
                }
                if (address.getText().length()<3){
                    flag = false;
                    address.setText("* Invalid address");
                    address.setStyle("-fx-text-fill: red;");
                }

                if(flag){
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

                }


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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Confirmation");
            alert.setContentText("Are you sure you want to delete the host?");

            ButtonType cancelButton = new ButtonType("Cancel");
            ButtonType okButton = new ButtonType("OK");

            alert.getButtonTypes().setAll(cancelButton, okButton);

            // Hiển thị hộp thoại cảnh báo và chờ người dùng phản hồi
            Optional<ButtonType> result = alert.showAndWait();

            // Xử lý phản hồi của người dùng
            if (result.isPresent() && result.get() == okButton) {
                ClientDAO clientDao = new ClientDAO();
                Client client = clientDao.getDataById(clientId.getText());
                File file = new File(GetRootLink.getRootPathForRoom(client.getClientImage()).toString());
                file.delete();
                clientDao.delete(clientId.getText());
                Stage currentStage = (Stage) btnDelete.getScene().getWindow();
                currentStage.close();
//                primaryStage.close();
            } else {
                // Người dùng đã chọn nút Cancel hoặc đóng hộp thoại
                // Không thực hiện việc xóa và không đóng cửa sổ
            }
        });

    }
}
