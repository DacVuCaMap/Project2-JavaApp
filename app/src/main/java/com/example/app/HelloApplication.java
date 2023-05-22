
package com.example.app;

import com.example.app.Controller.LoginScene;
import com.example.app.DB.DBGeneric;
import com.example.app.DB.HostDAO;
import com.example.app.DB.RoomDAO;
import com.example.app.DB.RoomImageDAO;
import com.example.app.Entity.Enum.RoomType;
import com.example.app.Entity.Host;
import com.example.app.Entity.Room;
import com.example.app.Entity.Validation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginScene.getStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneView/LoginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Apartment Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}