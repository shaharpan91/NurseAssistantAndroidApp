package com.g4bootcamp.nurseassistant;


import java.io.Serializable;

public class Patient implements Serializable {

    private String firstname;
    private String lastname;
    private String personalCode;
    private int room;
    private int floor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (floor != patient.floor) return false;
        if (id != patient.id) return false;
        if (!firstname.equals(patient.firstname)) return false;
        if (!lastname.equals(patient.lastname)) return false;
        if (!personalCode.equals(patient.personalCode)) return false;
        return (room == patient.room);
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        int hc = 0;
        char[] arr = lastname.toCharArray();
        for (char c : arr)
            if (c%2==0)
            {
                hc = hc - (int) c;
            } else
            {
                hc = hc + (int) c;
            }

        char[] arr2 = personalCode.toCharArray();
        for (char c : arr)
            if (c%2==0)
            {
                hc = hc - (int) c;
            } else
            {
                hc = hc + (int) c;
            }

        result = 31 * result + room;
        result = 31 * result + floor;
        result = 31 * result + id;
        return result;
    }



    private int id;

    Patient(String firstname, String lastname, String personalCode, int room, int floor, int id){

        this.firstname=firstname;
        this.lastname=lastname;
        this.personalCode=personalCode;
        this.room=room;
        this.floor=floor;
        this.id = id;

    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
