package com.example.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MyFXMLLoader {
    private static Pane view;
    public static Pane getView(String fileName){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyFXMLLoader.class.getResource(fileName));
            view=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        return view;
    }
}
