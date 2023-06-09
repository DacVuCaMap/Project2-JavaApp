package com.example.app.Data;

import com.example.app.Entity.MonthlyPrice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkingFile {
    public static MonthlyPrice readMonthlyPrice(){
        FileReader reader = null;
        try {
            reader= new FileReader(System.getProperty("user.dir")+"/src/main/java/com/example/app/Data/PriceMonthly.data");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line=bufferedReader.readLine();
            if (line!=null){
                String[] arr = line.split("-");
                return new MonthlyPrice(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]),Double.parseDouble(arr[2]));
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
