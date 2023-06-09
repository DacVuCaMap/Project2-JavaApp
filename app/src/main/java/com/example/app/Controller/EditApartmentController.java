package com.example.app.Controller;

import com.example.app.DB.*;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class EditApartmentController implements Initializable {
    @FXML TextField hostName;
    @FXML TextField apartmentName;
    @FXML TextField address;
    @FXML TextField apId;
    @FXML private ImageView imageAp;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Button btnImg;
    @FXML private Button btnRefresh;
    @FXML private Button btnSave;
    @FXML private Label tmpURL;
    Image img =null;
    public void setDetail(Apartment ap){
        img = new Image(GetRootLink.getRootPathForApartment(ap.getApartmentImage()).toString());
        apId.setText(ap.getApartmentId());
        apartmentName.setText(ap.getApartmentName());
        address.setText(ap.getAddress());
        imageAp.setImage(img);
        hostName.setText(ap.getHost().getHostName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {apartmentName, address};
        ApartmentDAO apartmentDAO = new ApartmentDAO();
//        Sự kiện khi click vào nút Edit sẽ cho phép sửa các ô input
        btnEdit.setOnMouseClicked(e->{
            for(TextField i : textFields){
                i.setEditable(true);
            }
            btnImg.setDisable(false);

//            Sự kiện click vào nút chọn ảnh
            btnImg.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    imageAp.setImage(image);
                    tmpURL.setText(selectedFile.toString());
                    System.out.println(selectedFile.toString());
                }});

//            Sự kiện khi chọn nút Save
            btnSave.setOnMouseClicked(event->{
                Boolean flag = true;

                if (apartmentName.getText().length()==0 || apartmentName.getText().length()<4){
                    apartmentName.setText("* Invalid Name");
                    flag=false;
                    apartmentName.setStyle("-fx-text-fill: red;");
                }
                if (address.getText().length()<5){
                    flag=false;
                    address.setText("* Invalid address");
                    address.setStyle("-fx-text-fill: red;");
                }
                if(flag){
                    if(tmpURL.getText()==""){
                        Apartment apartmentUpdate = new Apartment(
                                apartmentName.getText(),
                                address.getText()
                        );
                        apartmentDAO.updateNoImg(apartmentUpdate, apId.getText());
                    }else {
                        String absolute = tmpURL.getText();
                        Path absolutePath = Path.of(absolute);
                        Path destination = Path.of(System.getProperty("user.dir"), "src/main/resources/imageData/objectData/apartmentIMG");
                        try {
                            Files.copy(absolutePath, destination.resolve(absolutePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Path path = Paths.get(absolute);
                        String fileName = path.getFileName().toString();

                        Apartment newApartment = new Apartment(
                                apartmentName.getText(),
                                address.getText(),
                                fileName
                        );

                        apartmentDAO.update(newApartment, apId.getText());
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

//        Sự kiện khi click vào nút nạp lại
        btnRefresh.setOnMouseClicked(event->{
            Apartment apartment = new Apartment();
            apartment = apartmentDAO.getApById(apId.getText());
            System.out.println(apartment);
            img = new Image(GetRootLink.getRootPathForApartment(apartment.getApartmentImage()).toString());
            imageAp.setImage(img);
            apartmentName.setText(apartment.getApartmentName());
            address.setText(apartment.getAddress());
            for(TextField i: textFields){
                i.setEditable(false);
            }
            btnImg.setDisable(true);
        });

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
                ApartmentDAO apDao = new ApartmentDAO();
                Apartment apartment = apDao.getApById(apId.getText());
                File file = new File(GetRootLink.getRootPathForRoom(apartment.getApartmentImage()).toString());
                file.delete();
                apDao.delete(apId.getText());
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
