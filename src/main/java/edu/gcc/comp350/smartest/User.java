package edu.gcc.comp350.smartest;

import java.util.ArrayList;
public class User {
    private final int userID;
    private String name;
    private String major;
    private ArrayList<Course> gradReqs;

    public String getMajor() {
        return major;
    }

    public ArrayList<Course> getGradReqs() {
        return gradReqs;
    }

    public User(int userID, String name, String major) {
        this.userID = userID;
        this.name = name;
        this.major = major;
        this.gradReqs = new ArrayList<>();
    }

    //TODO: Check that there are no schedule conflicts or something

    public void changeName(String newName) {

    }

    public void changeMajor(String newMajor) {

    }

    public void setGradReqs(ArrayList<Course> req) {

    }

    public void LoadSchedule(){

    }

}
