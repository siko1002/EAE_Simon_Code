package com.example.booklibrary;

public class Author {
    Author(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }
    Author(String vorname, String nachname, int pid) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.pid = pid;
    }
    int pid;
    private String vorname;
    private String nachname;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        return "Author [vorname=" + vorname + ", nachname=" + nachname + "]";
    }
    public int getpId(){
        return this.pid;
    }
    public void setpId(int id){
        this.pid = id;
    }
}
