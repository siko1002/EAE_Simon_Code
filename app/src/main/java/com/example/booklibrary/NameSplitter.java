package com.example.booklibrary;

public class NameSplitter {
    static String SplitVorname(String author){
        String[] splitted = author.split(" ");
        String Vorname = splitted[0];
        String Nachname = splitted[splitted.length-1];
        return Vorname;
    }
    static String SplitNachname(String author){
            String[]splitted = author.split(" ");
            String Vorname = splitted[0];
            String Nachname = splitted[splitted.length - 1];
        if(Vorname.equals(Nachname)){
            return "";
        }else{
            return  Nachname;
        }
    }
}
