package com.example.app.Controller;


import com.example.app.DB.MySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TextField nameTextField;
    private Connection conn;
    @FXML
    private Button btnManagement;


    public void initialize() {
        try {
            conn = MySQLConnection.getConnection();
            String query = "SELECT adminName,adminEmail FROM tbladmin WHERE id = 1";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                nameTextField.setText(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void NextHoSoCaNhan(ActionEvent e){
        System.out.println("get go to Ho So Ca Nhan");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/LoginScene.fxml"));
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnManagement.setOnAction(e->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/sceneView/SelectMenuScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}


