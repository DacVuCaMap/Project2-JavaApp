package com.example.app.Controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class NotificationController implements Initializable {
    @FXML
    private Circle circleImg;

    @FXML
    private Label nofText;

    public void setNof(String str){
        nofText.setText(str);
        circleImg.setFill(new ImagePattern(new Image(getClass().getResource("/imageData/tickicon.png").toString())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void getNofView(String str){
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/sceneView/NotificationScene.fxml"));
            //load scene
            AnchorPane sceneRoot = fxmlLoader.load();
            //get instance controller
            NotificationController item = fxmlLoader.getController();
            item.setNof(str);
            //load scene
            Scene scene = new Scene(sceneRoot);
            stage.setResizable(false);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            // Lấy kích thước của màn hình
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double screenWidth = screenSize.getWidth();
            double screenHeight = screenSize.getHeight();

            // Đặt vị trí của cửa sổ ở góc phải dưới

            double x = screenWidth-300;
            double y = screenHeight-150-42;
            stage.setX(x);
            stage.setY(y);
            stage.show();
            //after 3 second stage will close
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
