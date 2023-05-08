 module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;

    //mysql
     requires mysql.connector.java;
     requires java.sql;
     //jBcrypt
     requires jBCrypt;
    opens com.example.app to javafx.fxml;
    exports com.example.app;
    opens com.example.app.Controller to javafx.fxml;
}