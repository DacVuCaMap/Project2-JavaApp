package com.example.app.Controller.Add;

import com.example.app.DB.*;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
import com.example.app.Entity.Room;
import com.example.app.Entity.Validation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    private DBGeneric<Client> clientDBGeneric = new ClientDAO();
    private String imgLink="user-default.png";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setOnAction(e->{
            boolean flag = true;
            //validation
            if (!Validation.isEmail(mailField.getText())){
                flag = false;
                mailNof.setText("* Invalid email");
            }
            //room chua co
            if (!Validation.isUserName(nameField.getText())){
                flag=false;
                nameNof.setText("* Invalid name");
            }
            if(!Validation.isNbr(idField.getText())){
                flag=false;
                idNof.setText("* Invalid ID");
            }
            if (!Validation.isNbr(phoneField.getText()) || phoneField.getText().length()<10){
                flag=false;
                phoneNof.setText("* Invalid phone");
            }
            if (dobField.getValue() == null){
                flag= false;
                dobNof.setText("* Not empty");
            }
            if (addressField.getText().length()<3){
                flag = false;
                addressNof.setText("* Invalid address");
            }
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
                //insert
                Client client = new Client(clientId,image,clientName,clientEmail,phone,address,clientDOB
                ,citizenID);
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
                imgLink = fileName;
                Path targetFile = GetRootLink.getRootPathForClient(fileName);
                try {
                    Files.copy(sourceFile,targetFile, StandardCopyOption.REPLACE_EXISTING);
                    circleImg.setFill(new ImagePattern(new Image(targetFile.toString())));
                    circleText.setText("");
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
    public List<String> getRoomBoxList(){
        List<String> list = new ArrayList<>();
        for (Room room : new RoomDAO().getAllData()){
            if (room.getStatus().getLabel()!="AVAILABLE"){
                list.add(room.getApartment().getApartmentName() + "    room:"+room.getRoomNumber());
            }
        }
        return list;
    }
}
