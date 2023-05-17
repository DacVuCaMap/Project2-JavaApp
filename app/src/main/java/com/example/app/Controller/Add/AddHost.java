package com.example.app.Controller.Add;
import com.example.app.Controller.HostController;
import com.example.app.Controller.LoginScene;
import com.example.app.DB.GetRootLink;
import com.example.app.DB.UserDB;
import com.example.app.Entity.User;
import com.example.app.Entity.Validation;
import javafx.event.ActionEvent;

import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.Entity.Host;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddHost implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    private TextField citizenId;

    @FXML
    private DatePicker hostDob;

    @FXML
    private TextField hostMail;

    @FXML
    private TextField hostName;

    @FXML
    private TextField hostPhone;

    @FXML
    private TextField hostaddress;

    @FXML
    private Button imgBtn;
    private static DBGeneric<Host> hostDBGeneric = new HostDAO();

    //nofication
    @FXML
    private Label idNof;
    @FXML
    private Label addressNof;
    @FXML
    private Label dobNof;
    @FXML
    private Label mailNof;

    @FXML
    private Label nameNof;

    @FXML
    private Label phoneNof;
    @FXML
    private Circle circleImg;
    @FXML
    private Label circleText;
    private String imgName = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // add to database
        addBtn.setOnAction(e->{
            // clear label
            nameNof.setText("");
            dobNof.setText("");
            addressNof.setText("");
            idNof.setText("");
            mailNof.setText("");
            phoneNof.setText("");
            boolean flag = true;
            // validation
            if (!Validation.isUserName(hostName.getText()) || hostName.getText().length()==0){
                flag=false;
                nameNof.setText("* Invalid host name");
            }
            if((hostDob.getValue()==null)){
                flag=false;
                dobNof.setText("* Date of birth not empty");
            }
            if (hostaddress.getText().length()<5){
                flag=false;
                addressNof.setText("* Invalid address");
            }
            //check citizenId is number
            if (!Validation.isNbr(citizenId.getText()) || citizenId.getText().length()<5){
                System.out.println(citizenId.getText());
                flag=false;
                idNof.setText("* Invalid ID");
            }
            if (!Validation.isEmail(hostMail.getText())){
                flag=false;
                mailNof.setText("* Invalid email");
            }
            if (!Validation.isNbr(hostPhone.getText()) || hostPhone.getText().length()!=10){
                flag=false;
                phoneNof.setText("* Invalid phone number");
            }
            if (flag){
                //get insert
                String hostId = buildId();
                String imgLink =imgName;
                if (imgName.length()==0){
                    imgLink = "user-default.png";
                }
                Host host = new Host(hostId,hostName.getText()
                        ,hostDob.getValue(),hostaddress.getText(),citizenId.getText(),imgLink,hostMail.getText(),hostPhone.getText());
                hostDBGeneric.insertData(host);
                //update in parent

                // Get the current stage
                Stage stage = (Stage) cancleBtn.getScene().getWindow();

                // Close the stage
                stage.close();
            }
        });


        cancleBtn.setOnAction(e->{
            // Get the current stage
            Stage stage = (Stage) cancleBtn.getScene().getWindow();

            // Close the stage
            stage.close();
        });


        imgBtn.setOnAction(e->{

            Stage stage = (Stage) imgBtn.getScene().getWindow();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF and JPG","*png","*jpg"));
            File selectedFile = fc.showOpenDialog(stage);
            if (selectedFile!=null){
                //select file Path
                Path sourecPath = Path.of(selectedFile.getAbsolutePath());

                //target file
                String fileName = buildId()+"img.png";
                imgName = fileName;
                Path targetFile = GetRootLink.getRootPath(fileName);
                try {
                    Files.copy(sourecPath,targetFile,StandardCopyOption.REPLACE_EXISTING);
                    Image image = new Image(targetFile.toString());
                    circleImg.setFill(new ImagePattern(image));
                    circleText.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                imgName="";
                circleText.setText("Avatar");
                System.out.println("no file selected");
            }

        });
    }
    public static String buildId(){
        int number=hostDBGeneric.getAllData().size()+1;
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "HT"+numberStr;
    }



}
