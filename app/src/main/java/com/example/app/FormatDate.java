package com.example.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    public static String formatDateString(String dateString) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatDateReverse(String dateString) {
        String inputPattern = "dd-MM-yyyy";
        String outputPattern = "yyyy-MM-dd";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
