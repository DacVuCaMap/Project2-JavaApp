package com.example.app.Controller.Add;

import com.example.app.DB.ApartmentDAO;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.GetRootLink;
import com.example.app.DB.RoomDAO;
import com.example.app.Entity.Apartment;
import com.example.app.Entity.Enum.StatusRoom;
import com.example.app.Entity.Host;
import com.example.app.Entity.Room;
import com.example.app.Entity.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddRoom implements Initializable {

    @FXML
    private ComboBox<String> apartmentBox;

    @FXML
    private Label apartmentNof;

    @FXML
    private Button btnAdd;

    @FXML
    private Button cancleBtn;

    @FXML
    private TextField desField;

    @FXML
    private Label desNof;

    @FXML
    private Button imgBtn1;

    @FXML
    private Button imgBtn2;

    @FXML
    private Button imgBtn3;

    @FXML
    private Button imgBtn4;
    @FXML
    private Button imgBtn5;

    @FXML
    private TextField numField;

    @FXML
    private Label numNof;

    @FXML
    private TextField priceField;

    @FXML
    private Label priceNof;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private Label typeNof;

    //img
    private String img1="";
    private String img2="";
    private String img3="";
    private String img4="";
    private String img5="";
    private static DBGeneric<Room> roomDBGeneric = new RoomDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apartmentBox.getItems().addAll(getApartmentList());
        String[] typeIntoChoiceBox = {"Studio","1k2n","1k1n","Duplex"};
        typeBox.getItems().addAll(typeIntoChoiceBox);
        btnAdd.setOnAction(e->{
            boolean flag = true;
            numNof.setText("");
            priceNof.setText("");
            apartmentNof.setText("");
            typeNof.setText("");
            desNof.setText("");
            //validation
            if (!Validation.isNbr(numField.getText()) || numField.getText().length()<3){
                flag=false;
                numNof.setText("* Invalid number");
            }
            if (!Validation.isNbr(priceField.getText()) || priceField.getText().length()==0){
                flag=false;
                priceNof.setText("* Invalid price");
            }
            if (apartmentBox.getValue()==null){
                flag=false;
                apartmentNof.setText("* Invalid apartment");
            }
            if (typeBox.getValue()==null){
                flag=false;
                typeNof.setText("* Invalid typeBox");
            }
            if (desField.getText().length()<5){
                flag=false;
                desNof.setText("* Invalid description");
            }
            //add to db
            if (flag){
                //get apartment
                String apartmentId = apartmentBox.getValue().substring(3,8);
                Apartment apartment = new ApartmentDAO().getApartmentbyId(apartmentId);
                String[] listImg = {img1,img2,img3,img4,img5};
                for (int i=0;i<listImg.length;i++){
                    if (listImg[i].length()==0){
                        listImg[i] = null;
                    }
                }
                Double price = Double.parseDouble(priceField.getText());
                Room room = new Room(buildId(),numField.getText(),price
                ,new RoomDAO().getRoomType(typeBox.getValue()),listImg[0],listImg[1]
                ,listImg[2],listImg[3],listImg[4],StatusRoom.A,apartment,desField.getText());
                roomDBGeneric.insertData(room);

                //close scene
                Stage stage = (Stage) btnAdd.getScene().getWindow();
                stage.close();
            }
        });

        cancleBtn.setOnAction(e->{
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
        });

        imgBtn1.setOnAction(e->{
            img1 = getImgLink(e);
            System.out.println(img1);
        });
        imgBtn2.setOnAction(e->{
            img2 = getImgLink(e);
        });
        imgBtn3.setOnAction(e->{
            img3 = getImgLink(e);
        });
        imgBtn4.setOnAction(e->{
            img4 = getImgLink(e);
        });
        imgBtn5.setOnAction(e->{
            img5 = getImgLink(e);
        });
    }

    public String getImgLink(ActionEvent e){
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
            Path targetFile = GetRootLink.getRootPathForRoom(fileName);
            try {
                Files.copy(sourceFile,targetFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return fileName;
    }
    public String buildId(){
        int number =0;
        if (roomDBGeneric.getAllData()!=null){
            number=roomDBGeneric.getAllData().size()+1;
        }
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "RM"+numberStr;
    }
    public List<String> getApartmentList(){
        List<String> list = new ArrayList<>();
        for (Apartment apartment : new ApartmentDAO().getAllData()){
            list.add("ID:"+apartment.getApartmentId()+"   Name:"+apartment.getApartmentName());
        }
        return list;
    }
}
