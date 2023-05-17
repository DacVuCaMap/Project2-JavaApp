package com.example.app.Controller.Add;

import com.example.app.DB.*;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
import com.example.app.Entity.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

public class AddClient implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private TextField addressField;

    @FXML
    private Label addressNof;

    @FXML
    private Button cancleBtn;

    @FXML
    private Circle circleImg;

    @FXML
    private Label circleText;

    @FXML
    private DatePicker dobField;

    @FXML
    private Label dobNof;

    @FXML
    private TextField idField;

    @FXML
    private Label idNof;

    @FXML
    private Button imgBtn;

    @FXML
    private TextField mailField;

    @FXML
    private Label mailNof;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameNof;

    @FXML
    private TextField phoneField;

    @FXML
    private Label phoneNof;

    @FXML
    private ComboBox<String> roomBox;

    @FXML
    private Label roomNof;
    private DBGeneric<Client> clientDBGeneric = new ClientDAO();
    private String imgLink="user-default.png";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomBox.getItems().addAll(getRoomBoxList());
        addBtn.setOnAction(e->{
            boolean flag = true;
            //validation


            //add
            if (flag){
                String clientId = buildId();
                String image = imgLink;
                String clientName = nameField.getText();
                String clientEmail = mailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                LocalDate clientDOB = dobField.getValue();
                String citizenID = idField.getText();
                Room room = getRoomMap(roomBox.getValue());
                System.out.println(room);

                //insert
                Client client = new Client(clientId,image,clientName,clientEmail,phone,address,clientDOB
                ,citizenID,room);
                clientDBGeneric.insertData(client);


                //close
                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.close();
            }
        });
        cancleBtn.setOnAction(e->{
            Stage stage = (Stage) cancleBtn.getScene().getWindow();
            stage.close();
        });
        imgBtn.setOnAction(e->{
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF and JPG","*png","*jpg"));
            File selectedFile = fc.showOpenDialog(stage);
            String fileName="";
            if (selectedFile!=null){
                //select file path
                Path sourceFile = Path.of(selectedFile.getAbsolutePath());

                //target file
                fileName = buildId()+"img.png";
                fileName = imgLink;
                Path targetFile = GetRootLink.getRootPathForRoom(fileName);
                try {
                    Files.copy(sourceFile,targetFile, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                imgLink = "user-default.png";
            }
        });
    }
    public String buildId(){
        int number =0;
        if (clientDBGeneric.getAllData()!=null){
            number=clientDBGeneric.getAllData().size()+1;
        }
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "CL"+numberStr;
    }
    public Room getRoomMap(String str){
        Map<String,Room> mapRoom = new HashMap<>();
        for(Room room : new RoomDAO().getAllData()){
            mapRoom.put(room.getApartment().getApartmentName() + "    room:"+room.getRoomNumber(),room);
        }
        Room room = mapRoom.get(str);
        return room;
    }
    public List<String> getRoomBoxList(){
        List<String> list = new ArrayList<>();
        for (Room room : new RoomDAO().getAllData()){
            list.add(room.getApartment().getApartmentName() + "    room:"+room.getRoomNumber());
        }
        return list;
    }
}
