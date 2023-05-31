package com.example.app.Controller;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
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
    Image img = null;

    public void setDetail(Host host) {
        img = new Image(String.valueOf(getClass().getResource(host.getHostImage())));
        hostId.setText(host.getHostId());
        name.setText(host.getHostName());
        dob.setText(String.valueOf(host.getDob()));
        address.setText(host.getAddress());
        citizenID.setText(host.getCitizenId());
        hostImg.setImage(img);
        email.setText(host.getHostEmail());
        phone.setText(host.getHostPhone());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField textFields[] = {hostId, name, dob, citizenID, email, phone};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
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
                }
            });

//            Sự kiện khi chọn nút Save
            btnSave.setOnMouseClicked(event->{

                Host updateHost = new Host(
                        hostId.getText(),
                        name.getText(),
                        LocalDate.parse(dob.getText(), formatter),
                        address.getText(),
                        citizenID.getText(),
                        hostImg.getImage().getUrl(),
                        email.getText(),
                        phone.getText()

                );

                hostDAO.update(updateHost, hostId.getText());
                for(TextField i: textFields){
                    i.setEditable(false);
                }
                address.setEditable(false);
                btnImg.setDisable(true);
            });
        });




    }

}

