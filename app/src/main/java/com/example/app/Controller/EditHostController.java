package com.example.app.Controller;
import com.example.app.DB.GetRootLink;
import com.example.app.DB.HostDAO;
import com.example.app.DB.RoomDAO;
import com.example.app.DB.RoomImageDAO;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
import com.example.app.Entity.RoomImg;
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

public class EditHostController implements Initializable {
    @FXML
    private TextArea address;
    @FXML
    private ImageView hostImg;
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
    private TextField hostId;
    @FXML
    private TextField dob;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML Label tmpURL;
    Image img = null;

    public void setDetail(Host host) {
        img = new Image(GetRootLink.getRootPath(host.getHostImage()).toString());
        hostId.setText(host.getHostId());
        name.setText(host.getHostName());
        dob.setText(FormatDate.formatDateString(String.valueOf(host.getDob())));
        address.setText(host.getAddress());
        citizenID.setText(host.getCitizenId());
        hostImg.setImage(img);
        email.setText(host.getHostEmail());
        phone.setText(host.getHostPhone());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {name, dob, citizenID, email, phone};
        HostDAO hostDAO = new HostDAO();

        //        Sự kiện khi click vào nút Edit sẽ cho phép sửa các ô input
        btnEdit.setOnMouseClicked(e-> {
            for (TextField i : textFields) {
                i.setEditable(true);
            }
            address.setEditable(true);
            btnImg.setDisable(false);

//            Sự kiện click vào nút chọn ảnh
            btnImg.setOnMouseClicked(event1 -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    hostImg.setImage(image);
                    tmpURL.setText(selectedFile.toString());
                }


            });

//            Sự kiện khi chọn nút Save
            btnSave.setOnMouseClicked(event->{
                Boolean flag = true;
                if (!Validation.isUserName(name.getText()) || name.getText().length()==0){
                    flag=false;
                    name.setText("* Invalid host name");
                    name.setStyle("-fx-text-fill: red;");
                }
                if((dob.getText()==null)){
                    flag=false;
                    dob.setText("* Date of birth not empty");
                    dob.setStyle("-fx-text-fill: red;");
                }
                if (address.getText().length()<5){
                    flag=false;
                    address.setText("* Invalid address");
                    address.setStyle("-fx-text-fill: red;");
                }
                //check citizenId is number
                if (!Validation.isNbr(citizenID.getText()) || citizenID.getText().length()<5){
                    flag=false;
                    citizenID.setText("* Invalid ID");
                    citizenID.setStyle("-fx-text-fill: red;");
                }
                if (!Validation.isEmail(email.getText())){
                    flag=false;
                    email.setText("* Invalid email");
                    email.setStyle("-fx-text-fill: red;");
                }
                if (!Validation.isNbr(phone.getText()) || phone.getText().length()!=10){
                    flag=false;
                    phone.setText("* Invalid phone number");
                    phone.setStyle("-fx-text-fill: red;");
                }

                if(flag){
                    if(tmpURL.getText()==""){
                        Host hostUpdate = new Host(
                                hostId.getText(),
                                name.getText(),
                                LocalDate.parse(FormatDate.formatDateReverse(dob.getText())),
                                address.getText(),
                                citizenID.getText(),
                                email.getText(),
                                phone.getText()

                        );
                        hostDAO.updateNoImg(hostUpdate, hostId.getText());
                    }else {
                        String absolute = tmpURL.getText();
                        Path absolutePath = Path.of(absolute);
                        Path destination = Path.of(System.getProperty("user.dir"), "src/main/resources/imageData/objectData/hostIMG");
                        try {
                            Files.copy(absolutePath, destination.resolve(absolutePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Path path = Paths.get(absolute);
                        String fileName = path.getFileName().toString();

                        Host newHost = new Host(
                                hostId.getText(),
                                name.getText(),
                                LocalDate.parse(FormatDate.formatDateReverse(dob.getText())),
                                address.getText(),
                                citizenID.getText(),
                                fileName,
                                email.getText(),
                                phone.getText()
                        );

                        hostDAO.update(newHost, hostId.getText());
                    }
                    tmpURL.setText("");
                    for(TextField i: textFields){
                        i.setEditable(false);
                    }
                    address.setEditable(false);
                    btnImg.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Edit success");
                    alert.setHeaderText(null);
                    alert.setContentText("Your update is complete!");
                    alert.showAndWait();
                }

            });
        });

        btnRefresh.setOnMouseClicked(event->{
            Host host = new Host();
            host = hostDAO.getHostById(hostId.getText());
            img = new Image(GetRootLink.getRootPath(host.getHostImage()).toString());
            hostId.setText(host.getHostId());
            hostImg.setImage(img);
            name.setText(host.getHostName());
            dob.setText(FormatDate.formatDateString(String.valueOf(host.getDob())));
            phone.setText(host.getHostPhone());
            email.setText(host.getHostEmail());
            address.setText(host.getAddress());
            citizenID.setText(host.getCitizenId());
            for(TextField i: textFields){
                i.setEditable(false);
            }
            address.setEditable(false);
            btnImg.setDisable(true);
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
                HostDAO hostDao = new HostDAO();
                Host host = hostDao.getHostById(hostId.getText());
                File file = new File(GetRootLink.getRootPathForRoom(host.getHostImage()).toString());
                file.delete();
                hostDao.delete(hostId.getText());
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

