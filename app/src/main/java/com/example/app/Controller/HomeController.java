package com.example.app.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeController {
    @FXML
    private TextField nameTextField;

    public void initialize() {
        String url = "jdbc:mysql://localhost:3306/apartmentmanager";
        String username = "root";
        String password = "123456";

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
}


