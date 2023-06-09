package com.example.app.Controller.Monthly;

import com.example.app.DB.MonthlyDAO;
import com.example.app.DB.MonthlyDetailsDAO;
import com.example.app.Entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MonthlyEnter implements Initializable {
    @FXML
    private Label billClient;

    @FXML
    private Label billApartment;

    @FXML
    private Label billHost;

    @FXML
    private Label billMonth;

    @FXML
    private Label billRoom;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label currentDate;

    @FXML
    private TextField elecNumber;

    @FXML
    private Label elecPrice;

    @FXML
    private Label labelValid;

    @FXML
    private Label servicePrice;

    @FXML
    private Label totalPrice;

    @FXML
    private TextField waterNumber;

    @FXML
    private Label waterPrice;
    private MonthlyPrice monthlyPrice ;
    private Monthly monthly;
    public void setData(Monthly monthly, MonthlyPrice monthlyPrice){
        this.monthly = monthly;
        this.monthlyPrice=monthlyPrice;
        LocalDate curDate=LocalDate.now();
        currentDate.setText(curDate.toString());
        billMonth.setText("Month: "+curDate.getMonth());
        billRoom.setText("Room "+monthly.getContract().getRoom().getRoomNumber());
        billClient.setText("Client: "+monthly.getContract().getClient().getClientName());
        billHost.setText("Host: "+monthly.getContract().getRoom().getApartment().getHost().getHostName());
        billApartment.setText("ApartMent: "+monthly.getContract().getRoom().getApartment().getApartmentName());
        elecPrice.setText("Electric price: "+monthlyPrice.getElectricPrice()+" $/number");
        waterPrice.setText("Water price :"+monthlyPrice.getWaterPrice()+" $/number");
        servicePrice.setText("Service price :"+monthlyPrice.getServicePrice()+" $");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        elecNumber.textProperty().addListener((observable,oldValue,newValue)->{
            System.out.println(waterNumber.getText().equals(""));
            if (Validation.isNbr(elecNumber.getText()) && !waterNumber.getText().equals("")
            && Validation.isNbr(waterNumber.getText())){
                setTotalPrice(Integer.valueOf(elecNumber.getText()),Integer.valueOf(waterNumber.getText()));
            }
        });
        waterNumber.textProperty().addListener((observable,oldValue,newValue)->{
            if (Validation.isNbr(elecNumber.getText()) && !elecNumber.getText().equals("")
                    && Validation.isNbr(waterNumber.getText())){
                setTotalPrice(Integer.valueOf(elecNumber.getText()),Integer.valueOf(waterNumber.getText()));
            }
        });
        confirmBtn.setOnAction(e->{
            //validation
            boolean flag=true;
            if (!Validation.isNbr(elecNumber.getText())){
                flag=false;
                labelValid.setText("Invalid");
            }
            if (!Validation.isNbr(waterNumber.getText())){
                flag=false;
                labelValid.setText("Invalid");
            }
            if (flag){
                String monthAndYear = monthly.getDate().getYear()+"-"+monthly.getDate().getMonth();
                MonthlyDetails monthlyDetails = new MonthlyDetails(
                    buildId(),monthly.getContract().getClient()
                        ,monthly.getContract().getRoom()
                        ,monthlyPrice.getElectricPrice(),Integer.valueOf(elecNumber.getText())
                        ,monthlyPrice.getWaterPrice(),Integer.valueOf(waterNumber.getText())
                        ,monthlyPrice.getServicePrice(),LocalDate.now(),monthAndYear
                );
                //insert
                new MonthlyDetailsDAO().insertData(monthlyDetails);
                //delete monthly
                new MonthlyDAO().deleteContractAndMonth(monthly);
                //update scene
                sharedMenuData.monthlySceneController.showDataPublic();
                Stage stage =(Stage) confirmBtn.getScene().getWindow();
                stage.close();
            }
        });
    }
    public void setTotalPrice(int elec,int water){
        double total = monthlyPrice.getServicePrice()+elec*monthlyPrice.getElectricPrice()+water*monthlyPrice.getWaterPrice();
        totalPrice.setText(total+" $");
    }
    public static String buildId(){
        int number=new MonthlyDetailsDAO().getAllData().size()+1;
        String numberStr;
        if (number<10){
            numberStr="00"+number;
        } else if (number>10 && number<100) {
            numberStr="0"+number;
        } else {
            numberStr=Integer.toString(number);
        }
        return "MD"+numberStr;
    }
}
