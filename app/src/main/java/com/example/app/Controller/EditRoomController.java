package com.example.app.Controller;

import com.example.app.DB.ClientDAO;
import com.example.app.DB.GetRootLink;
import com.example.app.DB.RoomDAO;
import com.example.app.DB.RoomImageDAO;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
import com.example.app.Entity.Enum.RoomType;
import com.example.app.Entity.Enum.StatusRoom;
import com.example.app.Entity.Room;
import com.example.app.Entity.RoomImg;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
    @FXML Button btnImg1;
    @FXML Button btnImg2;
    @FXML Button btnImg3;
    @FXML Button btnImg4;
    @FXML Button btnImg5;
    @FXML Label tmpURL1;
    @FXML Label tmpURL2;
    @FXML Label tmpURL3;
    @FXML Label tmpURL4;
    @FXML Label tmpURL5;
    @FXML TextField client;
    Image[] images = new Image[5];
    String[] tmpURL = new String[5];
    public void setDetail(Room room){

        tmpURL1.setText(room.getImg1());
        tmpURL2.setText(room.getImg2());
        tmpURL3.setText(room.getImg3());
        tmpURL4.setText(room.getImg4());
        tmpURL5.setText(room.getImg5());
        images[0] = new Image(GetRootLink.getRootPathForRoom(room.getImg1()).toString());
        images[1] = new Image(GetRootLink.getRootPathForRoom(room.getImg2()).toString());
        images[2] = new Image(GetRootLink.getRootPathForRoom(room.getImg3()).toString());
        images[3] = new Image(GetRootLink.getRootPathForRoom(room.getImg4()).toString());
        images[4] = new Image(GetRootLink.getRootPathForRoom(room.getImg5()).toString());
        roomId.setText(room.getRoomId());
        roomNumber.setText(room.getRoomNumber());
        price.setText(String.valueOf(room.getPrice()));
        apartment.setText(String.valueOf(room.getApartment().getAddress()));
        img1.setImage(images[0]);
        img2.setImage(images[1]);
        img3.setImage(images[2]);
        img4.setImage(images[3]);
        img5.setImage(images[4]);
        describle.setText(room.getDesRoom());
        roomType.setText(String.valueOf(room.getRoomType().getLabel()));
        status.setText(String.valueOf(room.getStatus().getLabel()));
        //client
        client.setDisable(true);
        if (room.getClient()!=null){
            client.setText(room.getClient().getClientName());
        }
        else{
            client.setText("* Empty");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(tmpURL1.getText());
        TextField textFields[] = {roomNumber, roomType, price, status,};
        RoomDAO roomDAO = new RoomDAO();
        RoomImageDAO roomImgDao = new RoomImageDAO();
//        Sự kiện khi click vào nút Edit sẽ cho phép sửa các ô input
        btnEdit.setOnMouseClicked(e->{
            for(TextField i : textFields){
                i.setEditable(true);
            }
            describle.setEditable(true);
            btnImg1.setDisable(false);
            btnImg2.setDisable(false);
            btnImg3.setDisable(false);
            btnImg4.setDisable(false);
            btnImg5.setDisable(false);

            String url1=tmpURL1.getText();
            String url2=tmpURL2.getText();
            String url3=tmpURL3.getText();
            String url4=tmpURL4.getText();
            String url5=tmpURL5.getText();
            String[] imgURL = {url1,url2,url3,
                    url4,url5};
            System.out.println(imgURL);
//            Sự kiện click vào nút chọn ảnh
            btnImg1.setOnMouseClicked(event1->{



                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    img1.setImage(image);
                    tmpURL1.setText(selectedFile.toString());
                }});

            btnImg2.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    img2.setImage(image);
                    tmpURL2.setText(selectedFile.toString());
                }});

            btnImg3.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    img3.setImage(image);
                    tmpURL3.setText(selectedFile.toString());
                }});

            btnImg4.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    img4.setImage(image);
                    tmpURL4.setText(selectedFile.toString());
                }});

            btnImg5.setOnMouseClicked(event1->{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chọn tệp tin hình ảnh");
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                // Lấy tệp tin được chọn
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // Tải ảnh từ tệp tin được chọn lên ImageView
                    Image image = new Image(selectedFile.toURI().toString());
                    img5.setImage(image);
                    tmpURL5.setText(selectedFile.toString());
                }});

//            Sự kiện khi chọn nút Save
            btnSave.setOnMouseClicked(event->{

                String[] newTmpURL = new String[5];
                newTmpURL[0] = tmpURL1.getText();
                newTmpURL[1] = tmpURL2.getText();
                newTmpURL[2] = tmpURL3.getText();
                newTmpURL[3] = tmpURL4.getText();
                newTmpURL[4] = tmpURL5.getText();
//                System.out.println(tmpURL[0]);
//                System.out.println(newTmpURL[0]);
                for(int j=0; j<5; j++) {
                    if (imgURL[j] != newTmpURL[j]) {

                        String absolute = newTmpURL[j];
                        Path absolutePath = Path.of(absolute);
                        Path destination = Path.of(System.getProperty("user.dir"), "src/main/resources/imageData/objectData/roomIMG");
                        try {
                            Files.copy(absolutePath, destination.resolve(absolutePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Path path = Paths.get(absolute);
                        newTmpURL[j] = path.getFileName().toString();

                    }
                }

                Room newRoom = new Room(
                        roomNumber.getText(),
                        Double.parseDouble(price.getText()),
                        RoomType.valueOf(roomType.getText()),
                        StatusRoom.valueOf(status.getText()),
                        describle.getText()
                );

                RoomImg newRoomImg = new RoomImg(
                        newTmpURL[0],
                        newTmpURL[1],
                        newTmpURL[2],
                        newTmpURL[3],
                        newTmpURL[4]
                );

                roomDAO.update(newRoom, roomId.getText());
                roomImgDao.update(newRoomImg,roomId.getText());
                for(TextField i: textFields){
                    i.setEditable(false);
                }
                describle.setEditable(false);
                btnImg1.setDisable(true);
                btnImg2.setDisable(true);
                btnImg3.setDisable(true);
                btnImg4.setDisable(true);
                btnImg5.setDisable(true);


            });

        });
        btnRefresh.setOnMouseClicked(e->{

        });

    }
}
