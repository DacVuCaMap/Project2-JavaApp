package com.example.app.Controller.Add;

import com.example.app.DB.*;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Client;
import com.example.app.Entity.Host;
import com.example.app.Entity.Validation;
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
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddApartment implements Initializable {
    @FXML
    private TextField addressField;

    @FXML
    private Label addressNof;

    @FXML
    private Button btnAdd;

    @FXML
    private Button cancleBtn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Label hostNof;

    @FXML
    private ImageView imageShow;

    @FXML
    private Button imgBtn;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameNof;
    private DBGeneric<Apartment> apartmentDBGeneric= new ApartmentDAO();
    private String imgLink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgLink = "apartment-default2.png";
        choiceBox.getItems().addAll(getChoiceList());

        btnAdd.setOnAction(e->{

            boolean flag=true;
            //validation
            nameNof.setText("");
            hostNof.setText("");
            addressNof.setText("");
            if (nameField.getText().length()==0 || nameField.getText().length()<4){
                nameNof.setText("* Invalid Name");
                flag=false;
            }
            if (choiceBox.getValue()==null){
                flag=false;
                hostNof.setText("* Host not empty");
            }
            if (addressField.getText().length()<5){
                flag=false;
                addressNof.setText("* Invalid address");
            }
            //get add
            if (flag){
                String hostId = choiceBox.getValue().toString().substring(3,8);
                Host hostFound = findHostById(hostId);
                Apartment apartment = new Apartment(buildId(),hostFound,nameField.getText()
                        ,addressField.getText(),imgLink);
                apartmentDBGeneric.insertData(apartment);
                Stage stage = (Stage) btnAdd.getScene().getWindow();
                stage.close();
            }
        });
        imgBtn.setOnAction(e->{
            Stage stage = (Stage) imgBtn.getScene().getWindow();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF and JPG","*png","*jpg"));
            File selectedFile = fc.showOpenDialog(stage);
            if (selectedFile!=null){
                //select file path
                Path sourceFile = Path.of(selectedFile.getAbsolutePath());

                //target file
                String fileName = buildId()+"img.png";
                imgLink = fileName;
                Path targetFile = GetRootLink.getRootPathForApartment(fileName);
                try {
                    Files.copy(sourceFile,targetFile, StandardCopyOption.REPLACE_EXISTING);
                    imageShow.setImage(new Image(targetFile.toString()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancleBtn.setOnAction(e->{
            Stage stage = (Stage) imgBtn.getScene().getWindow();
            stage.close();
        });
    }
    public List<String> getChoiceList(){
        List<String> rs = new ArrayList<>();
        for (Host host : new HostDAO().getAllData()){
            rs.add("ID:"+host.getHostId()+"   Name:"+host.getHostName()+"   Host email:"+host.getHostEmail());
        }
        return rs;
    }

    public String buildId(){
        int number =0;
        if (apartmentDBGeneric.getAllData()!=null){
            number=apartmentDBGeneric.getAllData().size()+1;
        }
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "AM"+numberStr;
    }
    public static Host findHostById(String hostId){
        DBGeneric<Host> hostDBGeneric = new HostDAO();
        List<Host> hostList = hostDBGeneric.getAllData();
        for (Host host:hostList){
            if (host.getHostId().equals(hostId)){
                return host;
            }
        }
        return null;
    }
}
