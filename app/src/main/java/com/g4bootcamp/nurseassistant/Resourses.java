package com.g4bootcamp.nurseassistant;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Resourses {

    static ArrayList<Patient> patientList = new ArrayList<>();
    static ArrayList<Patient> patientListBackup = new ArrayList<>();
    static ArrayList<Recipe> recipeList = new ArrayList<>();
    static ArrayList<Recipe> recipeListBackup = new ArrayList<>();
    static Map<Patient, ArrayList<Recipe>> mainMap = new HashMap<>();
    static String username;
    static String password;



    static String userstate;
    static String nursename;

    static String URLlogin = "http://192.168.8.226:8080/RestWebAPIExample/PostExampleService/login?key=SHARED_KEY";
    static String URLgetlist = "http://192.168.8.226:8080/RestWebAPIExample/PostMethod/patList?key=SHARED_KEY";
    //static String URLlogin = "http://192.168.3.30:8080/RestWebAPIExample/PostExampleService/login?key=SHARED_KEY";
    //static String URLgetlist = "http://192.168.3.30:8080/RestWebAPIExample/PostMethod/patList?key=SHARED_KEY";





    public static void loadMainMap(String jsonstring) throws IOException, JSONException {

        ArrayList<Patient> patients = new ArrayList<Patient>();

        JSONArray jsonArray = new JSONArray(jsonstring);


        //Patient
        String firstname;
        String lastname;
        String personalcode;
        int room;
        int floor;

        //Recipe
        String medname;
        String meddosage;
        String medtime;


        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject current = jsonArray.getJSONObject(i);

            firstname = current.getString("firstname");
            lastname = current.getString("lastname");
            personalcode = current.getString("personal code");
            room = Integer.parseInt(current.getString("room no"));
            floor = Integer.parseInt(current.getString("floor no"));

            medname = current.getString("medicine name");
            meddosage = current.getString("dosage type");
            medtime = current.getString("recipe time");


            if (mainMap.containsKey(new Patient(firstname, lastname, personalcode, room, floor, 0))) {

                ArrayList<Recipe> currentList = mainMap.get(new Patient(firstname, lastname, personalcode, room, floor, 0));

                currentList.add(new Recipe(medname, meddosage, medtime, 0));

                mainMap.put(new Patient(firstname, lastname, personalcode, room, floor, 0), currentList);

            } else {

                ArrayList<Recipe> currentRecipes = new ArrayList<>();
                currentRecipes.add(new Recipe(medname, meddosage, medtime, 0));
                mainMap.put(new Patient(firstname, lastname, personalcode, room, floor, 0), currentRecipes);

            }




        }

    }

    public static void loadNurseName(String s) throws JSONException {

        JSONObject jsonObject = new JSONObject(s);



        setUserstate(jsonObject.getString("Role"));
        setNursename(jsonObject.getString("Name"));
        Log.d("login", "state " + getUserstate());
        Log.d("login", "name " + getNursename());

    }

    public static void getPatientList() {

        mainMap.keySet();

        for (Map.Entry<Patient, ArrayList<Recipe>> currentmap : mainMap.entrySet()) {
            Patient key = currentmap.getKey();
            patientList.add(key);
        }

        patientListBackup = new ArrayList<>(patientList);


    }

    public static ArrayList<Recipe> getRecipeList(Patient patient) {

        recipeList = mainMap.get(patient);
        recipeListBackup = new ArrayList<>(recipeList);

        return recipeList;
    }

    public static void sortByName(){

        Log.d("sort","Sorted by name");
        Collections.sort(patientList, new Comparator<Patient>() {
            public int compare(Patient v1, Patient v2) {
                return v1.getFirstname().compareTo(v2.getFirstname());
            }
        });

    }

    public static void sortByRoom(){

        Log.d("sort","Sorted by room");
        Collections.sort(patientList, new Comparator<Patient>() {
            public int compare(Patient v1, Patient v2) {
                return v1.getRoom() - (v2.getRoom());
            }
        });

    }

    public static void sortByFloor(){

        Log.d("sort","Sorted by floor");
        Collections.sort(patientList, new Comparator<Patient>() {
            public int compare(Patient v1, Patient v2) {
                return v1.getFloor() - (v2.getFloor());
            }
        });

    }

    public static void filterByFloor(int floor){

        List<Patient> l=new ArrayList<>();
        for (Patient p: patientList) {

            if(p.getFloor() != floor){
                l.add(p);
            }
        }
        if (!l.isEmpty()) for (Patient p : l) patientList.remove(p);



    }

    public static void filterByRoom(int room){

        List<Patient> l=new ArrayList<>();

        for (Patient p: patientList) {

            if(p.getRoom() != room){
                l.add(p);

            }

        }

        if (!l.isEmpty()) for (Patient p : l) patientList.remove(p);



    }

    public static void resetMap(){

        patientList.clear();
        patientListBackup.clear();
        mainMap.clear();

    }

    public static void filterReset(){

        patientList = patientListBackup;

    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Resourses.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Resourses.password = password;
    }

    public static String getNursename() {
        return nursename;
    }

    public static void setNursename(String nursename) {
        Resourses.nursename = nursename;
    }

    public static String getUserstate() {
        return userstate;
    }

    public static void setUserstate(String userstate) {
        Resourses.userstate = userstate;
    }


}
