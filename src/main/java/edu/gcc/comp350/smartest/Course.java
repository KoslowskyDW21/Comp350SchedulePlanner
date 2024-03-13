package edu.gcc.comp350.smartest;
import java.util.ArrayList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    public static ArrayList<Course> database;
    private final String courseCode;
    private final String department;
    private final int level;
    private final String name;
    private final String professor;
    private String color;
    private final String startTime;
    private final String endTime;
    private final String description;
    private final int numCredits;
    private final ArrayList<Course[]> preReqs;
    private final ArrayList<Course[]> coReqs;
    private final int numSeats;
    private final String days;
    private final String semester;

    public ArrayList<Course[]> getPreReqs() {
        return preReqs;
    }

    public ArrayList<Course[]> getCoReqs() {
        return coReqs;
    }


//    public Course(String courseCode, String name, String professor, String color, String description,
//                  int numCredits, ArrayList<Course[]> preReqs, ArrayList<Course[]> coReqs,
//                  String startTime, String endTime, int numSeats, String days) {
//        this.courseCode = courseCode;
//        this.name = name;
//        this.professor = professor;
//        this.color = color;
//        this.description = description;
//        this.numCredits = numCredits;
//        this.preReqs = preReqs;
//        this.coReqs = coReqs;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.numSeats = numSeats;
//        this.days = days;
//    }

    public Course(String excelLine) {
        String[] tokens = excelLine.split(",");
        courseCode = tokens[2] + tokens[3] + tokens[4];
        department = tokens[2];
        level = Integer.parseInt(tokens[3]);
        name = tokens[5];
        professor = tokens[17] + " " + tokens[16];
        color = "white";
        if (tokens.length >= 20) {
            description = tokens[19];
        } else {
            description = "";
        }
        numCredits = Integer.parseInt(tokens[6]);
        numSeats = Integer.parseInt(tokens[7]);
        preReqs = new ArrayList<>();
        coReqs = new ArrayList<>();
        startTime = tokens[14];
        endTime = tokens[15];
        days = tokens[9] + tokens[10] + tokens[11] + tokens[12] + tokens[13];
        if (Integer.parseInt(tokens[1]) == 10) {
            semester = "Fall";
        } else { //tokens[5] == 30
            semester = "Spring";
        }

    }

    private void changeColor(String color) {

    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDepartment() {
        return department;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public String getColor() {
        return color;
    }

    public String getStartTimes() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    public int getNumCredits() {
        return numCredits;
    }
}

