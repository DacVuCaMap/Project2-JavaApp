package com.example.app.Controller;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Client;
import com.example.app.Entity.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditRoomController implements Initializable {
    @FXML TextField roomNumber;
    @FXML TextField roomId;
    @FXML TextField price;
    @FXML TextField roomType;
    @FXML TextArea describle;
    @FXML TextField status;
    @FXML TextField apartment;
    @FXML ImageView img1;
    @FXML ImageView img2;
    @FXML ImageView img3;
    @FXML ImageView img4;
    @FXML ImageView img5;
    @FXML Button btnEdit;
    @FXML Button btnSave;
    @FXML Button btnRefresh;
    @FXML Button btnDelete;
    Image[] images = new Image[5];
    public void setDetail(Room room){
//        images[1]=new Image(String.valueOf(getClass().getResource(room.getImg1())));
//        images[2]=new Image(String.valueOf(getClass().getResource(room.getImg2())));
//        images[3]=new Image(String.valueOf(getClass().getResource(room.getImg3())));
//        images[4]=new Image(String.valueOf(getClass().getResource(room.getImg4())));
//        images[5]=new Image(String.valueOf(getClass().getResource(room.getImg5())));
        roomId.setText(room.getRoomId());
        roomNumber.setText(room.getRoomNumber());
        price.setText(String.valueOf(room.getPrice()));
        roomType.setText(String.valueOf(room.getApartment()));
//        img1.setImage(images[1]);
//        img2.setImage(images[2]);
//        img3.setImage(images[3]);
//        img4.setImage(images[4]);
//        img5.setImage(images[5]);
        describle.setText(room.getDesRoom());
        status.setText(String.valueOf(room.getStatus()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {roomNumber, roomType, price, status};
        ClientDAO clientDAO = new ClientDAO();
//        Sự kiện khi click vào nút Edit sẽ cho phép sửa các ô input
        btnEdit.setOnMouseClicked(e->{
            for(TextField i : textFields){
                i.setEditable(true);
            }
            describle.setEditable(true);
//            btnImg.setDisable(false);

//            Sự kiện click vào nút chọn ảnh
//            changeImg.setOnMouseClicked(event1->{
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Chọn tệp tin hình ảnh");
//                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
//                fileChooser.getExtensionFilters().add(imageFilter);
//                // Lấy tệp tin được chọn
//                File selectedFile = fileChooser.showOpenDialog(null);
//                if (selectedFile != null) {
//                    // Tải ảnh từ tệp tin được chọn lên ImageView
//                    Image image = new Image(selectedFile.toURI().toString());
//                    avatar.setImage(image);
//                }});

//            Sự kiện khi chọn nút Save
//            btnSave.setOnMouseClicked(event->{

//                Room newRoom = new Room(
//                        roomId.getText(),
////                        .getImage().getUrl(),
//                        name.getText(),
//                        email.getText(),
//                        phone.getText(),
//                        address.getText(),
//                        LocalDate.parse(dob.getText(), formatter),
//                        citizenID.getText()
//                );
//
//                clientDAO.update(newClient, clientId.getText());
//                for(TextField i: textFields){
//                    i.setEditable(false);
//                }
//                address.setEditable(false);
//                changeImg.setDisable(true);
//            });
        });
    }
}
