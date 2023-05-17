package com.example.app.DB;

import java.nio.file.Path;

public class GetRootLink {
    public static Path getRootPath(String fileName){
        String projectRoot = System.getProperty("user.dir");
        String resourcePath = projectRoot + "/src/main/resources/imageData/objectData/hostIMG/"+fileName;
        return Path.of(resourcePath);
    }
    public static Path getRootPathForApartment(String fileName){
        String projectRoot = System.getProperty("user.dir");
        String resourcePath = projectRoot + "/src/main/resources/imageData/objectData/apartmentIMG/"+fileName;
        return Path.of(resourcePath);
    }
    public static Path getRootPathForRoom(String fileName){
        String projectRoot = System.getProperty("user.dir");
        String resourcePath = projectRoot + "/src/main/resources/imageData/objectData/roomIMG/"+fileName;
        return Path.of(resourcePath);
    }
    public static Path getRootPathForClient(String fileName){
        String projectRoot = System.getProperty("user.dir");
        String resourcePath = projectRoot + "/src/main/resources/imageData/objectData/clientIMG/"+fileName;
        return Path.of(resourcePath);
    }
}
