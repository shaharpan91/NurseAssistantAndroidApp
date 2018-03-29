package com.g4bootcamp.nurseassistant;


public class Recipe {

    private String name;
    private String dosage;
    private String time;
    private int id;



    public Recipe (String name, String dosage, String time, int id){

        this.name = name;
        this.dosage = dosage;
        this.time = time;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
