package com.example.app.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeController {
    @FXML
    private TextField nameTextField;

    public void initialize() {
        String url = "jdbc:mysql://localhost:3306/ApartmentManagement";
        String username = "root";
        String password = "hoangpr0";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT adminName,adminEmail FROM tbladmin WHERE id = 1";
            Statement statement = connection.createStatement();
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
}


