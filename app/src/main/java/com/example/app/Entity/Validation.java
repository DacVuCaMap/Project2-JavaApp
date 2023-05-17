package com.example.app.Entity;

public class Validation {
    public static boolean isPwd(String pwd){
        String regex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return pwd.matches(regex);
    }
    public static boolean isUserName(String name){
        String regex = "^[a-zA-Z].{4,}$";
        return name.matches(regex);
    }
    public static boolean isEmail(String email){
        String regex = "^(.+)@(.+).{8}$";
        return email.matches(regex);
    }
    // check is number
    public static boolean isNbr(String str){
        try{
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
